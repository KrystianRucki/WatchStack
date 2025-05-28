package org.example.watchstack.controller;

import org.example.watchstack.entity.Movie;
import org.example.watchstack.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@Tag(name = "Movies", description = "Operations for managing movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    @Operation(summary = "Retrieve all movies", description = "Returns a list of all movies from the database")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get movie by ID", description = "Returns movie details by ID")
    public ResponseEntity<Movie> getMovieById(
            @Parameter(description = "ID of the movie to retrieve", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new movie", description = "Adds a new movie to the database")
    public Movie createMovie(
            @Parameter(description = "Movie object to create")
            @RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing movie", description = "Updates an existing movie by ID")
    public Movie updateMovie(
            @Parameter(description = "ID of the movie to update", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Updated movie object")
            @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a movie", description = "Deletes the movie by ID")
    public ResponseEntity<Void> deleteMovie(
            @Parameter(description = "ID of the movie to delete", example = "1")
            @PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}