package org.example.watchstack.service;

import org.example.watchstack.entity.WatchlistItem;
import org.example.watchstack.repository.WatchlistItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WatchlistItemService {

    @Autowired
    private WatchlistItemRepository watchlistItemRepository;

    public List<WatchlistItem> getAllItems() {
        return watchlistItemRepository.findAll();
    }

    public WatchlistItem getItemById(Long id) {
        return watchlistItemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("WatchlistItem not found with id " + id));
    }

    public WatchlistItem createItem(WatchlistItem item) {
        return watchlistItemRepository.save(item);
    }

    public WatchlistItem updateItem(Long id, WatchlistItem details) {
        WatchlistItem item = getItemById(id);
        item.setPriority(details.getPriority());
        return watchlistItemRepository.save(item);
    }

    public void deleteItem(Long id) {
        WatchlistItem item = getItemById(id);
        watchlistItemRepository.delete(item);
    }
}