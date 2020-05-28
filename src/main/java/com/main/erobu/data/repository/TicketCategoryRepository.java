package com.main.erobu.data.repository;

import com.main.erobu.data.entry.TicketCategory;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TicketCategoryRepository extends JpaRepository<TicketCategory,Integer> {

    TicketCategory findByCategory(String categoryName);

    <S extends TicketCategory> List<S> findAll(Example<S> example);
}
