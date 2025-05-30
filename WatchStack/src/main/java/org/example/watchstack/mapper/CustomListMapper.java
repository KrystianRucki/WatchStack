// mapper/CustomListMapper.java
package org.example.watchstack.mapper;

import org.example.watchstack.dto.CustomListDto;
import org.example.watchstack.entity.CustomList;

public class CustomListMapper {
    public static CustomListDto toDto(CustomList entity) {
        if (entity == null) return null;
        CustomListDto dto = new CustomListDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static CustomList toEntity(CustomListDto dto) {
        if (dto == null) return null;
        CustomList entity = new CustomList();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
