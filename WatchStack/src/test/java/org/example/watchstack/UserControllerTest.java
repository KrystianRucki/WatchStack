package org.example.watchstack;

import org.example.watchstack.controller.UserController;
import org.example.watchstack.entity.User;
import org.example.watchstack.service.UserService;
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
        User u1 = new User(); u1.setUsername("user1");
        User u2 = new User(); u2.setUsername("user2");
        when(userService.getAllUsers()).thenReturn(Arrays.asList(u1, u2));

        List<User> users = userController.getAllUsers();

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
        when(userService.getUserById(id)).thenReturn(user);

        User result = userController.getUserById(id).getBody();

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        verify(userService).getUserById(id);
    }

    @Test
    @DisplayName("Should create a user via controller")
    void testCreateUser() {
        User input = new User();
        input.setUsername("newUser");
        User saved = new User();
        saved.setId(3L);
        saved.setUsername("newUser");
        when(userService.createUser(input)).thenReturn(saved);

        User result = userController.createUser(input);

        assertNotNull(result);
        assertEquals(3L, result.getId());
        assertEquals("newUser", result.getUsername());
        verify(userService).createUser(input);
    }

    @Test
    @DisplayName("Should update user via controller")
    void testUpdateUser() {
        long id = 1L;
        User updated = new User();
        updated.setUsername("updated");
        when(userService.updateUser(id, updated)).thenReturn(updated);

        User result = userController.updateUser(id, updated);

        assertNotNull(result);
        assertEquals("updated", result.getUsername());
        verify(userService).updateUser(id, updated);
    }

    @Test
    @DisplayName("Should delete user via controller")
    void testDeleteUser() {
        long id = 1L;
        doNothing().when(userService).deleteUser(id);

        userController.deleteUser(id);

        verify(userService).deleteUser(id);
    }
}
