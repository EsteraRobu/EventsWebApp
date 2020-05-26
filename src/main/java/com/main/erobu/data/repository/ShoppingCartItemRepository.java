package com.main.erobu.data.repository;

import com.main.erobu.data.entry.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem,Integer> {
}
