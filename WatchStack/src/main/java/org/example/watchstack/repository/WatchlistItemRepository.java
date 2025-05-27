package org.example.watchstack.repository;

import org.example.watchstack.entity.WatchlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistItemRepository extends JpaRepository<WatchlistItem, Long> {
}