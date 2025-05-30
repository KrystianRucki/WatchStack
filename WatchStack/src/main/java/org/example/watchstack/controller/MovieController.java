package org.example.watchstack.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.example.watchstack.dto.MovieDto;
import org.example.watchstack.mapper.MovieMapper;
import org.example.watchstack.entity.Movie;
import org.example.watchstack.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
@Tag(name = "Movies", description = "Operations for managing movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    @Operation(summary = "Retrieve all movies", description = "Returns a list of all movies from the database")
    public List<MovieDto> getAll() {
        return movieService.getAllMovies().stream()
                .map(MovieMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get movie by ID", description = "Retrieve a single movie entry by ID")
    public ResponseEntity<MovieDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(MovieMapper.toDto(movieService.getMovieById(id)));
    }

    @PostMapping
    @Operation(summary = "Create movie", description = "Create a new movie entry")
    public MovieDto create(@RequestBody Movie movie) {
        return MovieMapper.toDto(movieService.createMovie(movie));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update movie", description = "Update an existing movie entry")
    public MovieDto update(@PathVariable Long id, @RequestBody Movie m) {
        return MovieMapper.toDto(movieService.updateMovie(id, m));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete movie", description = "Delete a movie entry by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}