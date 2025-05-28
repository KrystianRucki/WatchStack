package org.example.watchstack.service;

import org.example.watchstack.entity.ListItem;
import org.example.watchstack.repository.ListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ListItemService {

    @Autowired
    private ListItemRepository listItemRepository;

    public List<ListItem> getAllItems() {
        return listItemRepository.findAll();
    }

    public ListItem getItemById(Long id) {
        return listItemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ListItem not found with id " + id));
    }

    public ListItem createItem(ListItem item) {
        return listItemRepository.save(item);
    }

    public ListItem updateItem(Long id, ListItem details) {
        ListItem item = getItemById(id);
        item.setPosition(details.getPosition());
        return listItemRepository.save(item);
    }

    public void deleteItem(Long id) {
        ListItem item = getItemById(id);
        listItemRepository.delete(item);
    }
}