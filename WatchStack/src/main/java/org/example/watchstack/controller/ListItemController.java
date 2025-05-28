package org.example.watchstack.controller;

import org.example.watchstack.entity.ListItem;
import org.example.watchstack.service.ListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/list-items")
public class ListItemController {

    @Autowired
    private ListItemService listItemService;

    @GetMapping
    public List<ListItem> getAllListItems() {
        return listItemService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListItem> getListItemById(@PathVariable Long id) {
        return ResponseEntity.ok(listItemService.getItemById(id));
    }

    @PostMapping
    public ListItem createListItem(@RequestBody ListItem listItem) {
        return listItemService.createItem(listItem);
    }

    @PutMapping("/{id}")
    public ListItem updateListItem(@PathVariable Long id, @RequestBody ListItem listItem) {
        return listItemService.updateItem(id, listItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListItem(@PathVariable Long id) {
        listItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}