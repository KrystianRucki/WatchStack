package org.example.watchstack;

import org.example.watchstack.mapper.ListItemMapper;
import org.example.watchstack.dto.ListItemDto;
import org.example.watchstack.entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ListItemMapperTest {
    @Test void testToDto() {
        ListItem item = new ListItem(); item.setId(1L); item.setPosition(3);
        CustomList list = new CustomList(); list.setId(10L);
        Movie movie = new Movie(); movie.setId(20L);
        item.setCustomList(list); item.setMovie(movie);

        ListItemDto dto = ListItemMapper.toDto(item);
        assertEquals(1L, dto.getId());
        assertEquals(10L, dto.getCustomListId());
        assertEquals(20L, dto.getMovieId());
        assertEquals(3, dto.getPosition());
    }

    @Test void testToEntity() {
        ListItemDto dto = new ListItemDto(); dto.setId(1L); dto.setPosition(4);
        ListItem item = ListItemMapper.toEntity(dto);
        assertEquals(1L, item.getId()); assertEquals(4, item.getPosition());
    }
}
