package org.example.watchstack.mapper;

import org.example.watchstack.dto.ListItemDto;
import org.example.watchstack.entity.ListItem;

public class ListItemMapper {
    public static ListItemDto toDto(ListItem entity) {
        if (entity == null) return null;
        ListItemDto dto = new ListItemDto();
        dto.setId(entity.getId());
        dto.setCustomListId(entity.getCustomList() != null ? entity.getCustomList().getId() : null);
        dto.setMovieId(entity.getMovie() != null ? entity.getMovie().getId() : null);
        dto.setPosition(entity.getPosition());
        return dto;
    }

    public static ListItem toEntity(ListItemDto dto) {
        if (dto == null) return null;
        ListItem entity = new ListItem();
        entity.setId(dto.getId());
        entity.setPosition(dto.getPosition());
        return entity;
    }
}
