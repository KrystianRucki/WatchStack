package org.example.watchstack.service;

import org.example.watchstack.entity.WatchedEntry;
import org.example.watchstack.repository.WatchedEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WatchedEntryService {

    private final WatchedEntryRepository watchedEntryRepository;

    public WatchedEntryService(WatchedEntryRepository watchedEntryRepository) {
        this.watchedEntryRepository = watchedEntryRepository;
    }

    public List<WatchedEntry> getAllEntries() {
        return watchedEntryRepository.findAll();
    }

    public WatchedEntry getEntryById(Long id) {
        return watchedEntryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("WatchedEntry not found with id " + id));
    }

    public WatchedEntry createEntry(WatchedEntry entry) {
        return watchedEntryRepository.save(entry);
    }

    public WatchedEntry updateEntry(Long id, WatchedEntry details) {
        WatchedEntry entry = getEntryById(id);
        entry.setWatchedAt(details.getWatchedAt());
        entry.setRating(details.getRating());
        entry.setComment(details.getComment());
        return watchedEntryRepository.save(entry);
    }

    public void deleteEntry(Long id) {
        WatchedEntry entry = getEntryById(id);
        watchedEntryRepository.delete(entry);
    }
}