package org.example.watchstack.controller;

import org.example.watchstack.entity.WatchedEntry;
import org.example.watchstack.service.WatchedEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watched-entries")
public class WatchedEntryController {

    @Autowired
    private WatchedEntryService watchedEntryService;

    @GetMapping
    public List<WatchedEntry> getAllWatchedEntries() {
        return watchedEntryService.getAllEntries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WatchedEntry> getWatchedEntryById(@PathVariable Long id) {
        return ResponseEntity.ok(watchedEntryService.getEntryById(id));
    }

    @PostMapping
    public WatchedEntry createWatchedEntry(@RequestBody WatchedEntry entry) {
        return watchedEntryService.createEntry(entry);
    }

    @PutMapping("/{id}")
    public WatchedEntry updateWatchedEntry(@PathVariable Long id, @RequestBody WatchedEntry entry) {
        return watchedEntryService.updateEntry(id, entry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWatchedEntry(@PathVariable Long id) {
        watchedEntryService.deleteEntry(id);
        return ResponseEntity.noContent().build();
    }
}