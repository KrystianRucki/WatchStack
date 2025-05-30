package org.example.watchstack.mapper;

import org.example.watchstack.dto.WatchedEntryDto;
import org.example.watchstack.entity.WatchedEntry;

public class WatchedEntryMapper {

    public static WatchedEntryDto toDto(WatchedEntry entry) {
        if (entry == null) return null;
        WatchedEntryDto dto = new WatchedEntryDto();
        dto.setId(entry.getId());
        dto.setWatchedAt(entry.getWatchedAt());
        dto.setRating(entry.getRating());
        dto.setComment(entry.getComment());

        if (entry.getUser() != null) {
            dto.setUserId(entry.getUser().getId());
            dto.setUsername(entry.getUser().getUsername());
        }

        if (entry.getMovie() != null) {
            dto.setMovieId(entry.getMovie().getId());
            dto.setMovieTitle(entry.getMovie().getTitle());
        }

        return dto;
    }
}
