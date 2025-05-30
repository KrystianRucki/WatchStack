package org.example.watchstack.mapper;

import org.example.watchstack.dto.WatchlistItemDto;
import org.example.watchstack.entity.WatchlistItem;
import org.example.watchstack.entity.PriorityType;

public class WatchlistItemMapper {

    public static WatchlistItemDto toDto(WatchlistItem entity) {
        if (entity == null) return null;
        WatchlistItemDto dto = new WatchlistItemDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        dto.setMovieId(entity.getMovie() != null ? entity.getMovie().getId() : null);
        dto.setPriority(entity.getPriority() != null ? entity.getPriority().name() : null);
        dto.setAddedAt(entity.getAddedAt());
        return dto;
    }

    public static WatchlistItem toEntity(WatchlistItemDto dto) {
        if (dto == null) return null;
        WatchlistItem entity = new WatchlistItem();
        entity.setId(dto.getId());
        entity.setPriority(dto.getPriority() != null ? PriorityType.valueOf(dto.getPriority()) : null);
        entity.setAddedAt(dto.getAddedAt());
        return entity;
    }
}
