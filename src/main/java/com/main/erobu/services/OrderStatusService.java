package com.main.erobu.services;

import com.main.erobu.data.entry.OrderStatus;
import com.main.erobu.data.repository.OrderStatusRepository;
import com.main.erobu.dto.OrderStatusDTO;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderStatusService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public OrderStatusDTO findByStatus(String status) throws ObjectTransferException {
        List<OrderStatus> orderStatusList = orderStatusRepository.findByStatus(status);
        if(orderStatusList.size() <= 0){
            throw new ObjectTransferException("Data not found");

        }
        return EntityUtils.orderStatusToOrderStatusDTO(orderStatusList.get(0));
    }
}
