package org.example.watchstack.controller;

import org.example.watchstack.entity.WatchlistItem;
import org.example.watchstack.service.WatchlistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist-items")
@Tag(name = "WatchlistItems", description = "Operations for managing watchlist items")
public class WatchlistItemController {

    @Autowired
    private WatchlistItemService watchlistItemService;

    @GetMapping
    @Operation(summary = "Retrieve all watchlist items", description = "Returns a list of all watchlist items")
    public List<WatchlistItem> getAllWatchlistItems() {
        return watchlistItemService.getAllItems();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get watchlist item by ID", description = "Returns watchlist item details by ID")
    public ResponseEntity<WatchlistItem> getWatchlistItemById(
            @Parameter(description = "ID of the watchlist item to retrieve", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(watchlistItemService.getItemById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new watchlist item", description = "Adds a new item to the watchlist")
    public WatchlistItem createWatchlistItem(
            @Parameter(description = "WatchlistItem object to create")
            @RequestBody WatchlistItem watchlistItem) {
        return watchlistItemService.createItem(watchlistItem);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing watchlist item", description = "Updates the priority of an existing watchlist item by ID")
    public WatchlistItem updateWatchlistItem(
            @Parameter(description = "ID of the watchlist item to update", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Updated watchlist item object")
            @RequestBody WatchlistItem watchlistItem) {
        return watchlistItemService.updateItem(id, watchlistItem);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a watchlist item", description = "Deletes the watchlist item by ID")
    public ResponseEntity<Void> deleteWatchlistItem(
            @Parameter(description = "ID of the watchlist item to delete", example = "1")
            @PathVariable Long id) {
        watchlistItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
