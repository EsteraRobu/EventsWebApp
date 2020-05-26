package com.main.erobu.services;

import com.main.erobu.data.entry.OrderItem;
import com.main.erobu.data.repository.OrderItemRepository;
import com.main.erobu.dto.OrderItemDTO;
import com.main.erobu.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public void save(OrderItemDTO orderItemDTO){
        OrderItem orderItem = EntityUtils.orderItemDTOToOrderItem(orderItemDTO);
        orderItemRepository.save(orderItem);
    }
}
