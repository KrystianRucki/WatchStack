package org.example.watchstack;

import org.example.watchstack.service.MovieService;
import org.example.watchstack.entity.Movie;
import org.example.watchstack.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    private MovieRepository repo;
    private MovieService svc;

    @BeforeEach
    void setUp() {
        repo = mock(MovieRepository.class);
        svc = new MovieService(repo);
    }

    @Test @DisplayName("Get all movies")
    void testGetAll() {
        Movie m1 = new Movie(); m1.setTitle("A");
        Movie m2 = new Movie(); m2.setTitle("B");
        when(repo.findAll()).thenReturn(Arrays.asList(m1, m2));

        List<Movie> list = svc.getAllMovies();
        assertEquals(2, list.size());
    }

    @Test @DisplayName("Get movie by id not found")
    void testGetByIdNotFound() {
        when(repo.findById(10L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> svc.getMovieById(10L));
    }

    @Test @DisplayName("Create movie")
    void testCreate() {
        Movie m = new Movie(); m.setTitle("X");
        when(repo.save(m)).thenReturn(m);
        Movie out = svc.createMovie(m);
        assertEquals("X", out.getTitle());
    }

    @Test @DisplayName("Update movie")
    void testUpdate() {
        Movie existing = new Movie(); existing.setTitle("old");
        Movie details = new Movie(); details.setTitle("new");
        when(repo.findById(5L)).thenReturn(Optional.of(existing));
        when(repo.save(existing)).thenReturn(existing);

        Movie out = svc.updateMovie(5L, details);
        assertEquals("new", out.getTitle());
    }

    @Test @DisplayName("Delete movie")
    void testDelete() {
        Movie m = new Movie();
        when(repo.findById(7L)).thenReturn(Optional.of(m));

        svc.deleteMovie(7L);
        verify(repo).delete(m);
    }
}
