package org.example.watchstack.controller;

import org.example.watchstack.entity.ListItem;
import org.example.watchstack.service.ListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/api/list-items")
@Tag(name = "ListItems", description = "Operations for managing list items")
public class ListItemController {

    @Autowired
    private ListItemService listItemService;

    @GetMapping
    @Operation(summary = "Retrieve all list items", description = "Returns a list of all list items")
    public List<ListItem> getAllListItems() {
        return listItemService.getAllItems();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get list item by ID", description = "Returns list item details by ID")
    public ResponseEntity<ListItem> getListItemById(
            @Parameter(description = "ID of the list item to retrieve", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(listItemService.getItemById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new list item", description = "Adds a new item to a custom list")
    public ListItem createListItem(
            @Parameter(description = "ListItem object to create")
            @RequestBody ListItem listItem) {
        return listItemService.createItem(listItem);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing list item", description = "Updates an existing list item position by ID")
    public ListItem updateListItem(
            @Parameter(description = "ID of the list item to update", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Updated list item object")
            @RequestBody ListItem listItem) {
        return listItemService.updateItem(id, listItem);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a list item", description = "Deletes the list item by ID")
    public ResponseEntity<Void> deleteListItem(
            @Parameter(description = "ID of the list item to delete", example = "1")
            @PathVariable Long id) {
        listItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}