package org.example.watchstack;

import org.example.watchstack.controller.UserController;
import org.example.watchstack.dto.UserDto;
import org.example.watchstack.entity.User;
import org.example.watchstack.service.UserService;
import org.example.watchstack.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserService userService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    @DisplayName("Should return all users via controller")
    void testGetAllUsers() {
        User u1 = new User(); u1.setId(1L); u1.setUsername("user1"); u1.setEmail("e1"); u1.setPassword("p1");
        User u2 = new User(); u2.setId(2L); u2.setUsername("user2"); u2.setEmail("e2"); u2.setPassword("p2");

        when(userService.getAllUsers()).thenReturn(Arrays.asList(u1, u2));

        List<UserDto> users = userController.getAll();

        assertEquals(2, users.size());
        assertEquals("user1", users.get(0).getUsername());
        assertEquals("user2", users.get(1).getUsername());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    @DisplayName("Should return user by ID via controller")
    void testGetUserById() {
        long id = 1L;
        User user = new User();
        user.setId(id);
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("secret");

        when(userService.getUserById(id)).thenReturn(user);

        UserDto result = userController.getById(id).getBody();

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        verify(userService).getUserById(id);
    }

    @Test
    @DisplayName("Should create a user via controller")
    void testCreateUser() {
        UserDto dto = new UserDto();
        dto.setUsername("newUser");
        dto.setEmail("email@example.com");
        dto.setPassword("123");

        User saved = new User();
        saved.setId(3L);
        saved.setUsername("newUser");
        saved.setEmail("email@example.com");
        saved.setPassword("123");

        when(userService.createUser(any(User.class))).thenReturn(saved);

        UserDto result = userController.create(dto);

        assertNotNull(result);
        assertEquals(3L, result.getId());
        assertEquals("newUser", result.getUsername());
        verify(userService).createUser(any(User.class));
    }

    @Test
    @DisplayName("Should update user via controller")
    void testUpdateUser() {
        long id = 1L;
        UserDto dto = new UserDto();
        dto.setUsername("updated");
        dto.setEmail("up@example.com");
        dto.setPassword("pass");

        User updatedUser = new User();
        updatedUser.setId(id);
        updatedUser.setUsername("updated");
        updatedUser.setEmail("up@example.com");
        updatedUser.setPassword("pass");

        when(userService.updateUser(eq(id), any(User.class))).thenReturn(updatedUser);

        UserDto result = userController.update(id, dto);

        assertNotNull(result);
        assertEquals("updated", result.getUsername());
        verify(userService).updateUser(eq(id), any(User.class));
    }

    @Test
    @DisplayName("Should delete user via controller")
    void testDeleteUser() {
        long id = 1L;
        doNothing().when(userService).deleteUser(id);

        userController.delete(id);

        verify(userService).deleteUser(id);
    }
}
