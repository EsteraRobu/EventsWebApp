package com.main.erobu.data.repository;

import com.main.erobu.data.entry.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {


}
