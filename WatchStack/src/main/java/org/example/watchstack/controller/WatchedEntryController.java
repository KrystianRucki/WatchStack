package org.example.watchstack.controller;

import org.example.watchstack.entity.WatchedEntry;
import org.example.watchstack.service.WatchedEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/api/watched-entries")
@Tag(name = "WatchedEntries", description = "Operations for managing watched entries")
public class WatchedEntryController {

    @Autowired
    private WatchedEntryService watchedEntryService;

    @GetMapping
    @Operation(summary = "Retrieve all watched entries", description = "Returns a list of all watched entries")
    public List<WatchedEntry> getAllWatchedEntries() {
        return watchedEntryService.getAllEntries();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get watched entry by ID", description = "Returns watched entry details by ID")
    public ResponseEntity<WatchedEntry> getWatchedEntryById(
            @Parameter(description = "ID of the watched entry to retrieve", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(watchedEntryService.getEntryById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new watched entry", description = "Adds a new watched entry")
    public WatchedEntry createWatchedEntry(
            @Parameter(description = "WatchedEntry object to create")
            @RequestBody WatchedEntry entry) {
        return watchedEntryService.createEntry(entry);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing watched entry", description = "Updates an existing watched entry by ID")
    public WatchedEntry updateWatchedEntry(
            @Parameter(description = "ID of the watched entry to update", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Updated watched entry object")
            @RequestBody WatchedEntry entry) {
        return watchedEntryService.updateEntry(id, entry);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a watched entry", description = "Deletes the watched entry by ID")
    public ResponseEntity<Void> deleteWatchedEntry(
            @Parameter(description = "ID of the watched entry to delete", example = "1")
            @PathVariable Long id) {
        watchedEntryService.deleteEntry(id);
        return ResponseEntity.noContent().build();
    }
}