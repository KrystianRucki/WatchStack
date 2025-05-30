package org.example.watchstack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.watchstack.dto.ListItemDto;
import org.example.watchstack.entity.ListItem;
import org.example.watchstack.mapper.ListItemMapper;
import org.example.watchstack.service.ListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/list-items")
@Tag(name = "ListItems", description = "Manage List Items")
public class ListItemController {

    @Autowired
    private ListItemService service;

    @GetMapping
    @Operation(summary = "Get all list items")
    public List<ListItemDto> getAll() {
        return service.getAllItems().stream()
                .map(ListItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a list item by ID")
    public ResponseEntity<ListItemDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ListItemMapper.toDto(service.getItemById(id)));
    }

    @PostMapping
    @Operation(summary = "Create a new list item")
    public ResponseEntity<ListItemDto> create(@RequestBody ListItemDto dto) {
        ListItem entity = ListItemMapper.toEntity(dto);
        ListItem saved = service.createItem(entity);
        return ResponseEntity.ok(ListItemMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a list item")
    public ResponseEntity<ListItemDto> update(@PathVariable Long id, @RequestBody ListItemDto dto) {
        ListItem updated = service.updateItem(id, ListItemMapper.toEntity(dto));
        return ResponseEntity.ok(ListItemMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a list item")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
