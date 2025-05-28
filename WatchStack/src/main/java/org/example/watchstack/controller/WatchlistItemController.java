package org.example.watchstack.controller;

import org.example.watchstack.entity.WatchlistItem;
import org.example.watchstack.service.WatchlistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist-items")
public class WatchlistItemController {

    @Autowired
    private WatchlistItemService watchlistItemService;

    @GetMapping
    public List<WatchlistItem> getAllWatchlistItems() {
        return watchlistItemService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WatchlistItem> getWatchlistItemById(@PathVariable Long id) {
        return ResponseEntity.ok(watchlistItemService.getItemById(id));
    }

    @PostMapping
    public WatchlistItem createWatchlistItem(@RequestBody WatchlistItem watchlistItem) {
        return watchlistItemService.createItem(watchlistItem);
    }

    @PutMapping("/{id}")
    public WatchlistItem updateWatchlistItem(@PathVariable Long id, @RequestBody WatchlistItem watchlistItem) {
        return watchlistItemService.updateItem(id, watchlistItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWatchlistItem(@PathVariable Long id) {
        watchlistItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
