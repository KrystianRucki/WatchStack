package org.example.watchstack;

import org.example.watchstack.mapper.CustomListMapper;
import org.example.watchstack.dto.CustomListDto;
import org.example.watchstack.entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomListMapperTest {
    @Test void testToDto() {
        CustomList list = new CustomList(); list.setId(1L); list.setName("Top");
        list.setDescription("Top 10"); User u = new User(); u.setId(42L);
        list.setUser(u);

        CustomListDto dto = CustomListMapper.toDto(list);

        assertEquals(1L, dto.getId());
        assertEquals("Top", dto.getName());
        assertEquals("Top 10", dto.getDescription());
        assertEquals(42L, dto.getUserId());
    }

    @Test void testToEntity() {
        CustomListDto dto = new CustomListDto();
        dto.setId(2L); dto.setName("Watch later"); dto.setDescription("...");
        CustomList list = CustomListMapper.toEntity(dto);
        assertEquals("Watch later", list.getName());
        assertEquals("...", list.getDescription());
    }
}
