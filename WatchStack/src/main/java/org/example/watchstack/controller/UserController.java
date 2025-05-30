package org.example.watchstack.controller;

import org.example.watchstack.dto.UserDto;
import org.example.watchstack.mapper.UserMapper;
import org.example.watchstack.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Operations for managing users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Retrieve all users")
    public List<UserDto> getAll() {
        return userService.getAllUsers().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(UserMapper.toDto(userService.getUserById(id)));
    }

    @PostMapping
    @Operation(summary = "Create user")
    public UserDto create(@RequestBody UserDto dto) {
        return UserMapper.toDto(userService.createUser(UserMapper.toEntity(dto)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto dto) {
        return UserMapper.toDto(userService.updateUser(id, UserMapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}