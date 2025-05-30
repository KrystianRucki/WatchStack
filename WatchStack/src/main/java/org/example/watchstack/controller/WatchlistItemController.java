package org.example.watchstack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.watchstack.dto.WatchlistItemDto;
import org.example.watchstack.entity.WatchlistItem;
import org.example.watchstack.mapper.WatchlistItemMapper;
import org.example.watchstack.service.WatchlistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/watchlist-items")
@Tag(name = "WatchlistItems", description = "Manage Watchlist Items")
public class WatchlistItemController {

    @Autowired
    private WatchlistItemService service;

    @GetMapping
    @Operation(summary = "Get all watchlist items")
    public List<WatchlistItemDto> getAll() {
        return service.getAllItems().stream()
                .map(WatchlistItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a single watchlist item")
    public ResponseEntity<WatchlistItemDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(WatchlistItemMapper.toDto(service.getItemById(id)));
    }

    @PostMapping
    @Operation(summary = "Create a new watchlist item")
    public ResponseEntity<WatchlistItemDto> create(@RequestBody WatchlistItemDto dto) {
        WatchlistItem entity = WatchlistItemMapper.toEntity(dto);
        WatchlistItem saved = service.createItem(entity);
        return ResponseEntity.ok(WatchlistItemMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing watchlist item")
    public ResponseEntity<WatchlistItemDto> update(@PathVariable Long id, @RequestBody WatchlistItemDto dto) {
        WatchlistItem updated = service.updateItem(id, WatchlistItemMapper.toEntity(dto));
        return ResponseEntity.ok(WatchlistItemMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a watchlist item")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
