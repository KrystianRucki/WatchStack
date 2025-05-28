package org.example.watchstack;

import org.example.watchstack.service.WatchlistItemService;
import org.example.watchstack.entity.WatchlistItem;
import org.example.watchstack.repository.WatchlistItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WatchlistItemServiceTest {

    private WatchlistItemRepository repo;
    private WatchlistItemService svc;

    @BeforeEach
    void setUp() {
        repo = mock(WatchlistItemRepository.class);
        svc = new WatchlistItemService(repo);
    }

    @Test @DisplayName("Get all watchlist items")
    void testGetAll() {
        when(repo.findAll()).thenReturn(Arrays.asList(new WatchlistItem(), new WatchlistItem()));
        assertEquals(2, svc.getAllItems().size());
    }

    @Test @DisplayName("Get item by id not found")
    void testGetByIdNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> svc.getItemById(1L));
    }

    @Test @DisplayName("Create watchlist item")
    void testCreate() {
        WatchlistItem w = new WatchlistItem();
        when(repo.save(w)).thenReturn(w);
        assertSame(w, svc.createItem(w));
    }

    @Test @DisplayName("Update watchlist item")
    void testUpdate() {
        WatchlistItem existing = new WatchlistItem(); existing.setPriority(null);
        WatchlistItem details = new WatchlistItem(); details.setPriority(null);
        when(repo.findById(2L)).thenReturn(Optional.of(existing));
        when(repo.save(existing)).thenReturn(existing);
        WatchlistItem out = svc.updateItem(2L, details);
        assertNotNull(out);
    }

    @Test @DisplayName("Delete watchlist item")
    void testDelete() {
        WatchlistItem w = new WatchlistItem();
        when(repo.findById(3L)).thenReturn(Optional.of(w));
        svc.deleteItem(3L);
        verify(repo).delete(w);
    }
}
