package com.main.erobu.services;

import com.main.erobu.data.entry.OrderDetails;
import com.main.erobu.data.repository.OrderDetailsRepository;
import com.main.erobu.dto.OrderDetailsDTO;
import com.main.erobu.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public void save(OrderDetailsDTO orderDetailsDTO){
        OrderDetails orderDetails = EntityUtils.orderDetailsDTOToOrderDetails(orderDetailsDTO);
        orderDetailsRepository.save(orderDetails);
    }
}
