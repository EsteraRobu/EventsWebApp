package com.main.erobu.data.repository;

import com.main.erobu.data.entry.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
