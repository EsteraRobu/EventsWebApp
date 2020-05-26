package com.main.erobu.data.repository;

import com.main.erobu.data.entry.Client;
import com.main.erobu.data.entry.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findByClient(Client client);
}
