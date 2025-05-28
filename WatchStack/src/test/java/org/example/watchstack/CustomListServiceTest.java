package org.example.watchstack;

import org.example.watchstack.service.CustomListService;
import org.example.watchstack.entity.CustomList;
import org.example.watchstack.repository.CustomListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomListServiceTest {

    private CustomListRepository repo;
    private CustomListService svc;

    @BeforeEach
    void setup() {
        repo = mock(CustomListRepository.class);
        svc = new CustomListService(repo);
    }

    @Test @DisplayName("Get all lists")
    void testGetAll() {
        when(repo.findAll()).thenReturn(Arrays.asList(new CustomList(), new CustomList()));
        assertEquals(2, svc.getAllLists().size());
    }

    @Test @DisplayName("Get list by id not found")
    void testGetByIdNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> svc.getListById(1L));
    }

    @Test @DisplayName("Create list")
    void testCreate() {
        CustomList cl = new CustomList();
        when(repo.save(cl)).thenReturn(cl);
        assertSame(cl, svc.createList(cl));
    }

    @Test @DisplayName("Update list")
    void testUpdate() {
        CustomList existing = new CustomList(); existing.setName("old");
        CustomList details = new CustomList(); details.setName("new");
        when(repo.findById(2L)).thenReturn(Optional.of(existing));
        when(repo.save(existing)).thenReturn(existing);
        CustomList out = svc.updateList(2L, details);
        assertEquals("new", out.getName());
    }

    @Test @DisplayName("Delete list")
    void testDelete() {
        CustomList cl = new CustomList();
        when(repo.findById(3L)).thenReturn(Optional.of(cl));
        svc.deleteList(3L);
        verify(repo).delete(cl);
    }
}
