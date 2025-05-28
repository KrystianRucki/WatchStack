package org.example.watchstack.service;

import org.example.watchstack.entity.CustomList;
import org.example.watchstack.repository.CustomListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomListService {

    private final CustomListRepository customListRepository;

    public CustomListService(CustomListRepository customListRepository) {
        this.customListRepository = customListRepository;
    }

    public List<CustomList> getAllLists() {
        return customListRepository.findAll();
    }

    public CustomList getListById(Long id) {
        return customListRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("CustomList not found with id " + id));
    }

    public CustomList createList(CustomList list) {
        return customListRepository.save(list);
    }

    public CustomList updateList(Long id, CustomList details) {
        CustomList list = getListById(id);
        list.setName(details.getName());
        list.setDescription(details.getDescription());
        return customListRepository.save(list);
    }

    public void deleteList(Long id) {
        CustomList list = getListById(id);
        customListRepository.delete(list);
    }
}