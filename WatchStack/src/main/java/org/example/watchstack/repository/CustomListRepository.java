package org.example.watchstack.repository;

import org.example.watchstack.entity.CustomList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomListRepository extends JpaRepository<CustomList, Long> {
}