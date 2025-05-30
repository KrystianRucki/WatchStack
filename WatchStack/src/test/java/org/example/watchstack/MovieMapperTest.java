package org.example.watchstack;

import org.example.watchstack.mapper.MovieMapper;
import org.example.watchstack.dto.MovieDto;
import org.example.watchstack.entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MovieMapperTest {
    @Test void testToDto() {
        Movie m = new Movie();
        m.setId(1L); m.setTitle("Matrix"); m.setYear(1999);
        MovieDto dto = MovieMapper.toDto(m);

        assertEquals(1L, dto.getId());
        assertEquals("Matrix", dto.getTitle());
        assertEquals(1999, dto.getYear());
    }
}
