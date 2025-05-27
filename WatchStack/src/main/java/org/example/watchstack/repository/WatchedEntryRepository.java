package org.example.watchstack.repository;

import org.example.watchstack.entity.WatchedEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchedEntryRepository extends JpaRepository<WatchedEntry, Long> {
}