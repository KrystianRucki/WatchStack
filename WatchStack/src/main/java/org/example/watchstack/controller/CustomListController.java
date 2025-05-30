package org.example.watchstack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.watchstack.dto.CustomListDto;
import org.example.watchstack.entity.CustomList;
import org.example.watchstack.mapper.CustomListMapper;
import org.example.watchstack.service.CustomListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/custom-lists")
@Tag(name = "CustomLists", description = "Manage Custom Lists")
public class CustomListController {

    @Autowired
    private CustomListService service;

    @GetMapping
    @Operation(summary = "Get all custom lists")
    public List<CustomListDto> getAll() {
        return service.getAllLists().stream()
                .map(CustomListMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a custom list by ID")
    public ResponseEntity<CustomListDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(CustomListMapper.toDto(service.getListById(id)));
    }

    @PostMapping
    @Operation(summary = "Create a new custom list")
    public ResponseEntity<CustomListDto> create(@RequestBody CustomListDto dto) {
        CustomList entity = CustomListMapper.toEntity(dto);
        CustomList saved = service.createList(entity);
        return ResponseEntity.ok(CustomListMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a custom list")
    public ResponseEntity<CustomListDto> update(@PathVariable Long id, @RequestBody CustomListDto dto) {
        CustomList updated = service.updateList(id, CustomListMapper.toEntity(dto));
        return ResponseEntity.ok(CustomListMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a custom list")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteList(id);
        return ResponseEntity.noContent().build();
    }
}
