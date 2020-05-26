package com.main.erobu.data.repository;

import com.main.erobu.data.entry.Client;
import com.main.erobu.data.entry.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    List<ShoppingCart> findByClient(Client client);
}
