package org.example.watchstack;

import org.example.watchstack.dto.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DtoUnitTest {

    @Test
    void testWatchedEntryDto() {
        WatchedEntryDto dto = new WatchedEntryDto();
        dto.setId(1L);
        dto.setUserId(10L);
        dto.setMovieId(20L);
        dto.setUsername("john");
        dto.setMovieTitle("Matrix");
        dto.setWatchedAt(LocalDateTime.of(2023, 1, 1, 12, 0));
        dto.setRating(8);
        dto.setComment("Good");

        assertEquals(1L, dto.getId());
        assertEquals(10L, dto.getUserId());
        assertEquals(20L, dto.getMovieId());
        assertEquals("john", dto.getUsername());
        assertEquals("Matrix", dto.getMovieTitle());
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), dto.getWatchedAt());
        assertEquals(8, dto.getRating());
        assertEquals("Good", dto.getComment());
    }

    @Test
    void testWatchlistItemDto() {
        WatchlistItemDto dto = new WatchlistItemDto();
        dto.setId(2L);
        dto.setUserId(11L);
        dto.setMovieId(21L);
        dto.setPriority("HIGH");
        LocalDateTime now = LocalDateTime.now();
        dto.setAddedAt(now);

        assertEquals(2L, dto.getId());
        assertEquals(11L, dto.getUserId());
        assertEquals(21L, dto.getMovieId());
        assertEquals("HIGH", dto.getPriority());
        assertEquals(now, dto.getAddedAt());
    }

    @Test
    void testListItemDto() {
        ListItemDto dto = new ListItemDto();
        dto.setId(3L);
        dto.setCustomListId(12L);
        dto.setMovieId(22L);
        dto.setPosition(1);

        assertEquals(3L, dto.getId());
        assertEquals(12L, dto.getCustomListId());
        assertEquals(22L, dto.getMovieId());
        assertEquals(1, dto.getPosition());
    }

    @Test
    void testCustomListDto() {
        CustomListDto dto = new CustomListDto();
        dto.setId(4L);
        dto.setUserId(13L);
        dto.setName("Favorites");
        dto.setDescription("My favorite movies");

        assertEquals(4L, dto.getId());
        assertEquals(13L, dto.getUserId());
        assertEquals("Favorites", dto.getName());
        assertEquals("My favorite movies", dto.getDescription());
    }

    @Test
    void testMovieDto() {
        MovieDto dto = new MovieDto();
        dto.setId(5L);
        dto.setTitle("Inception");
        dto.setYear(2010);

        assertEquals(5L, dto.getId());
        assertEquals("Inception", dto.getTitle());
        assertEquals(2010, dto.getYear());
    }
}
