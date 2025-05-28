package org.example.watchstack.controller;

import org.example.watchstack.entity.CustomList;
import org.example.watchstack.service.CustomListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/api/custom-lists")
@Tag(name = "CustomLists", description = "Operations for managing custom lists")
public class CustomListController {

    @Autowired
    private CustomListService customListService;

    @GetMapping
    @Operation(summary = "Retrieve all custom lists", description = "Returns all custom lists")
    public List<CustomList> getAllCustomLists() {
        return customListService.getAllLists();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get custom list by ID", description = "Returns custom list details by ID")
    public ResponseEntity<CustomList> getCustomListById(
            @Parameter(description = "ID of the custom list to retrieve", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(customListService.getListById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new custom list", description = "Adds a new custom list")
    public CustomList createCustomList(
            @Parameter(description = "CustomList object to create")
            @RequestBody CustomList customList) {
        return customListService.createList(customList);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing custom list", description = "Updates an existing custom list by ID")
    public CustomList updateCustomList(
            @Parameter(description = "ID of the custom list to update", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Updated custom list object")
            @RequestBody CustomList customList) {
        return customListService.updateList(id, customList);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a custom list", description = "Deletes the custom list by ID")
    public ResponseEntity<Void> deleteCustomList(
            @Parameter(description = "ID of the custom list to delete", example = "1")
            @PathVariable Long id) {
        customListService.deleteList(id);
        return ResponseEntity.noContent().build();
    }
}