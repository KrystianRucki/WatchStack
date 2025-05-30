package org.example.watchstack.mapper;

import org.example.watchstack.dto.MovieDto;
import org.example.watchstack.entity.Movie;

public class MovieMapper {
    public static MovieDto toDto(Movie movie) {
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setYear(movie.getYear());
        return dto;
    }
}