package org.example.watchstack;

import org.example.watchstack.service.WatchedEntryService;
import org.example.watchstack.entity.WatchedEntry;
import org.example.watchstack.repository.WatchedEntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WatchedEntryServiceTest {

    private WatchedEntryRepository repo;
    private WatchedEntryService svc;

    @BeforeEach
    void init() {
        repo = mock(WatchedEntryRepository.class);
        svc = new WatchedEntryService(repo);
    }

    @Test @DisplayName("Get all entries")
    void testGetAll() {
        WatchedEntry e1 = new WatchedEntry(); e1.setWatchedAt(LocalDateTime.now());
        WatchedEntry e2 = new WatchedEntry(); e2.setWatchedAt(LocalDateTime.now());
        when(repo.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<WatchedEntry> list = svc.getAllEntries();
        assertEquals(2, list.size());
    }

    @Test @DisplayName("Get entry not found")
    void testGetByIdNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> svc.getEntryById(1L));
    }

    @Test @DisplayName("Create entry")
    void testCreate() {
        WatchedEntry e = new WatchedEntry(); e.setWatchedAt(LocalDateTime.now());
        when(repo.save(e)).thenReturn(e);
        assertNotNull(svc.createEntry(e));
    }

    @Test @DisplayName("Update entry")
    void testUpdate() {
        WatchedEntry existing = new WatchedEntry(); existing.setComment("old");
        WatchedEntry details = new WatchedEntry(); details.setComment("new");
        when(repo.findById(2L)).thenReturn(Optional.of(existing));
        when(repo.save(existing)).thenReturn(existing);

        WatchedEntry out = svc.updateEntry(2L, details);
        assertEquals("new", out.getComment());
    }

    @Test @DisplayName("Delete entry")
    void testDelete() {
        WatchedEntry e = new WatchedEntry();
        when(repo.findById(3L)).thenReturn(Optional.of(e));

        svc.deleteEntry(3L);
        verify(repo).delete(e);
    }
}
