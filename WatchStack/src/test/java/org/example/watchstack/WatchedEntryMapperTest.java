package org.example.watchstack;

import org.example.watchstack.mapper.WatchedEntryMapper;
import org.example.watchstack.dto.WatchedEntryDto;
import org.example.watchstack.entity.Movie;
import org.example.watchstack.entity.User;
import org.example.watchstack.entity.WatchedEntry;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WatchedEntryMapperTest {

    @Test
    void testToDto_allFieldsMapped() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("john_doe");

        Movie movie = new Movie();
        movie.setId(2L);
        movie.setTitle("Inception");

        WatchedEntry entry = new WatchedEntry();
        entry.setId(100L);
        entry.setUser(user);
        entry.setMovie(movie);
        entry.setWatchedAt(LocalDateTime.of(2023, 5, 10, 18, 30));
        entry.setRating(9);
        entry.setComment("Excellent movie!");

        // Act
        WatchedEntryDto dto = WatchedEntryMapper.toDto(entry);

        // Assert
        assertEquals(100L, dto.getId());
        assertEquals(1L, dto.getUserId());
        assertEquals("john_doe", dto.getUsername());
        assertEquals(2L, dto.getMovieId());
        assertEquals("Inception", dto.getMovieTitle());
        assertEquals(LocalDateTime.of(2023, 5, 10, 18, 30), dto.getWatchedAt());
        assertEquals(9, dto.getRating());
        assertEquals("Excellent movie!", dto.getComment());
    }

    @Test
    void testToDto_handlesNulls() {
        // Arrange
        WatchedEntry entry = new WatchedEntry();
        entry.setId(200L);
        entry.setWatchedAt(null);
        entry.setRating(0);
        entry.setComment(null);
        entry.setUser(null);
        entry.setMovie(null);

        // Act
        WatchedEntryDto dto = WatchedEntryMapper.toDto(entry);

        // Assert
        assertEquals(200L, dto.getId());
        assertNull(dto.getUserId());
        assertNull(dto.getUsername());
        assertNull(dto.getMovieId());
        assertNull(dto.getMovieTitle());
        assertNull(dto.getWatchedAt());
        assertEquals(0, dto.getRating());
        assertNull(dto.getComment());
    }

    @Test
    void testToDto_nullInputReturnsNull() {
        // Act & Assert
        assertNull(WatchedEntryMapper.toDto(null));
    }
}
