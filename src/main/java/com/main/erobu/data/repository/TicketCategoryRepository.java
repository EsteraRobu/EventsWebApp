package com.main.erobu.data.repository;

import com.main.erobu.data.entry.TicketCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketCategoryRepository extends JpaRepository<TicketCategory,Integer> {

    TicketCategory findByCategory(String categoryName);
}
