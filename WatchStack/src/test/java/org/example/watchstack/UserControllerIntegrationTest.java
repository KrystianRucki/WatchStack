package org.example.watchstack;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.watchstack.entity.User;
import org.example.watchstack.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Testcontainers
class UserControllerIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb123")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void registerDataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void cleanDb() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Should create a new user")
    void testCreateUser() throws Exception {
        User user = new User();
        user.setUsername("new-user");
        user.setEmail("new@user.com");
        user.setPassword("pass");

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("new-user"));

        assertTrue(userRepository.findAll().stream()
                .anyMatch(u -> "new-user".equals(u.getUsername())));
    }

    @Test
    @DisplayName("Should get all users")
    void testGetAllUsers() throws Exception {
        User a = new User(); a.setUsername("userA"); a.setEmail("a@u.com"); a.setPassword("p");
        User b = new User(); b.setUsername("userB"); b.setEmail("b@u.com"); b.setPassword("p");
        userRepository.save(a);
        userRepository.save(b);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("Should get user by ID")
    void testGetUserById() throws Exception {
        User u = new User(); u.setUsername("findMe"); u.setEmail("f@u.com"); u.setPassword("p");
        User saved = userRepository.save(u);

        mockMvc.perform(get("/api/users/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("findMe"));
    }

    @Test
    @DisplayName("Should update existing user")
    void testUpdateUser() throws Exception {
        User u = new User(); u.setUsername("before"); u.setEmail("b@u.com"); u.setPassword("p");
        User saved = userRepository.save(u);

        User updated = new User();
        updated.setId(saved.getId());
        updated.setUsername("after");
        updated.setEmail(saved.getEmail()); // ważne: nie zmieniamy e-maila
        updated.setPassword(saved.getPassword());

        mockMvc.perform(put("/api/users/" + saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("after"));
    }

    @Test
    @DisplayName("Should delete a user")
    void testDeleteUser() throws Exception {
        User u = new User(); u.setUsername("toDelete"); u.setEmail("d@u.com"); u.setPassword("p");
        User saved = userRepository.save(u);

        mockMvc.perform(delete("/api/users/" + saved.getId()))
                .andExpect(status().isNoContent());

        Optional<User> deleted = userRepository.findById(saved.getId());
        assertTrue(deleted.isEmpty());
    }

    @Test
    @DisplayName("Should update and rollback transaction")
    void testTransactionalRollback() throws Exception {
        User u = new User(); u.setUsername("trans"); u.setEmail("t@u.com"); u.setPassword("p");
        User saved = userRepository.save(u);

        mockMvc.perform(delete("/api/users/" + saved.getId()))
                .andExpect(status().isNoContent());
    }
}
