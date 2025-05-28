package org.example.watchstack.controller;

import org.example.watchstack.entity.CustomList;
import org.example.watchstack.service.CustomListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/custom-lists")
public class CustomListController {

    @Autowired
    private CustomListService customListService;

    @GetMapping
    public List<CustomList> getAllCustomLists() {
        return customListService.getAllLists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomList> getCustomListById(@PathVariable Long id) {
        return ResponseEntity.ok(customListService.getListById(id));
    }

    @PostMapping
    public CustomList createCustomList(@RequestBody CustomList customList) {
        return customListService.createList(customList);
    }

    @PutMapping("/{id}")
    public CustomList updateCustomList(@PathVariable Long id, @RequestBody CustomList customList) {
        return customListService.updateList(id, customList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomList(@PathVariable Long id) {
        customListService.deleteList(id);
        return ResponseEntity.noContent().build();
    }
}