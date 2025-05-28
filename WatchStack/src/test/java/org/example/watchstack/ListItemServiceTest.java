package org.example.watchstack;

import org.example.watchstack.service.ListItemService;
import org.example.watchstack.entity.ListItem;
import org.example.watchstack.repository.ListItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListItemServiceTest {

    private ListItemRepository repo;
    private ListItemService svc;

    @BeforeEach
    void init() {
        repo = mock(ListItemRepository.class);
        svc = new ListItemService(repo);
    }

    @Test @DisplayName("Get all items")
    void testGetAll() {
        when(repo.findAll()).thenReturn(Arrays.asList(new ListItem(), new ListItem()));
        assertEquals(2, svc.getAllItems().size());
    }

    @Test @DisplayName("Get by id not found")
    void testGetByIdNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> svc.getItemById(1L));
    }

    @Test @DisplayName("Create item")
    void testCreate() {
        ListItem item = new ListItem();
        when(repo.save(item)).thenReturn(item);
        assertSame(item, svc.createItem(item));
    }

    @Test @DisplayName("Update item")
    void testUpdate() {
        ListItem existing = new ListItem(); existing.setPosition(1);
        ListItem details = new ListItem(); details.setPosition(2);
        when(repo.findById(2L)).thenReturn(Optional.of(existing));
        when(repo.save(existing)).thenReturn(existing);
        ListItem out = svc.updateItem(2L, details);
        assertEquals(2, out.getPosition());
    }

    @Test @DisplayName("Delete item")
    void testDelete() {
        ListItem item = new ListItem();
        when(repo.findById(3L)).thenReturn(Optional.of(item));
        svc.deleteItem(3L);
        verify(repo).delete(item);
    }
}
