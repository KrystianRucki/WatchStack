package org.example.watchstack;

import org.example.watchstack.mapper.WatchlistItemMapper;
import org.example.watchstack.dto.WatchlistItemDto;
import org.example.watchstack.entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WatchlistItemMapperTest {

    @Test
    void testToDto() {
        WatchlistItem item = new WatchlistItem();
        item.setId(1L);
        item.setPriority(PriorityType.MEDIUM);
        item.setAddedAt(LocalDateTime.of(2023, 1, 1, 12, 0));

        User user = new User();
        user.setId(10L);
        item.setUser(user);

        Movie movie = new Movie();
        movie.setId(20L);
        item.setMovie(movie);

        WatchlistItemDto dto = WatchlistItemMapper.toDto(item);

        assertEquals(1L, dto.getId());
        assertEquals("MEDIUM", dto.getPriority());
        assertEquals(10L, dto.getUserId());
        assertEquals(20L, dto.getMovieId());
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), dto.getAddedAt());
    }

    @Test
    void testToEntity() {
        WatchlistItemDto dto = new WatchlistItemDto();
        dto.setId(2L);
        dto.setPriority("HIGH");
        dto.setAddedAt(LocalDateTime.of(2024, 2, 2, 14, 30));

        WatchlistItem entity = WatchlistItemMapper.toEntity(dto);

        assertEquals(2L, entity.getId());
        assertEquals(PriorityType.HIGH, entity.getPriority());
        assertEquals(LocalDateTime.of(2024, 2, 2, 14, 30), entity.getAddedAt());
    }

    @Test
    void testNullValues() {
        assertNull(WatchlistItemMapper.toDto(null));
        assertNull(WatchlistItemMapper.toEntity(null));
    }
}
