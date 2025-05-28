package org.example.watchstack;

import org.example.watchstack.entity.User;
import org.example.watchstack.service.UserService;
import org.example.watchstack.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("Should return all users")
    void testGetAllUsers() {
        User u1 = new User(); u1.setUsername("u1");
        User u2 = new User(); u2.setUsername("u2");
        when(userRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should throw when user not found by ID")
    void testGetUserByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        NoSuchElementException ex = assertThrows(NoSuchElementException.class,
                () -> userService.getUserById(1L));
        assertTrue(ex.getMessage().contains("User not found"));
    }

    @Test
    @DisplayName("Should return user by ID")
    void testGetUserById() {
        User u = new User(); u.setUsername("u");
        when(userRepository.findById(2L)).thenReturn(Optional.of(u));

        User result = userService.getUserById(2L);
        assertEquals("u", result.getUsername());
        verify(userRepository).findById(2L);
    }

    @Test
    @DisplayName("Should create a new user")
    void testCreateUser() {
        User u = new User(); u.setUsername("new");
        when(userRepository.save(u)).thenReturn(u);

        User result = userService.createUser(u);
        assertEquals("new", result.getUsername());
        verify(userRepository).save(u);
    }

    @Test
    @DisplayName("Should update existing user")
    void testUpdateUser() {
        User existing = new User(); existing.setUsername("old");
        User details = new User();   details.setUsername("new");
        when(userRepository.findById(3L)).thenReturn(Optional.of(existing));
        when(userRepository.save(existing)).thenReturn(existing);

        User result = userService.updateUser(3L, details);
        assertEquals("new", result.getUsername());
        verify(userRepository).findById(3L);
        verify(userRepository).save(existing);
    }

    @Test
    @DisplayName("Should delete user")
    void testDeleteUser() {
        User u = new User();
        when(userRepository.findById(4L)).thenReturn(Optional.of(u));

        userService.deleteUser(4L);
        verify(userRepository).delete(u);
    }
}
