package com.main.erobu.data.repository;

import com.main.erobu.data.entry.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory,Integer> {
    Optional<EventCategory> findById(Integer id);

    EventCategory findByCategory(String category);
}
