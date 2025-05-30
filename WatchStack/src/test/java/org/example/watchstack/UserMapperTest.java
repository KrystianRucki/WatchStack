package org.example.watchstack;

import org.example.watchstack.mapper.UserMapper;
import org.example.watchstack.dto.UserDto;
import org.example.watchstack.entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    @Test void testToDto() {
        User user = new User();
        user.setId(1L); user.setUsername("admin");
        user.setEmail("admin@example.com"); user.setPassword("pass");

        UserDto dto = UserMapper.toDto(user);

        assertEquals(1L, dto.getId());
        assertEquals("admin", dto.getUsername());
        assertEquals("admin@example.com", dto.getEmail());
        assertEquals("pass", dto.getPassword());
    }

    @Test void testToEntity() {
        UserDto dto = new UserDto();
        dto.setId(2L); dto.setUsername("user");
        dto.setEmail("user@mail.com"); dto.setPassword("secret");

        User user = UserMapper.toEntity(dto);

        assertEquals(2L, user.getId());
        assertEquals("user", user.getUsername());
        assertEquals("user@mail.com", user.getEmail());
        assertEquals("secret", user.getPassword());
    }
}
