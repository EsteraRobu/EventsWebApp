package com.main.erobu.services;

import com.main.erobu.data.entry.Client;
import com.main.erobu.data.entry.Order;
import com.main.erobu.data.entry.OrderItem;
import com.main.erobu.data.repository.OrderItemRepository;
import com.main.erobu.data.repository.OrderRepository;
import com.main.erobu.data.repository.OrderStatusRepository;
import com.main.erobu.dto.*;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.util.Constants;
import com.main.erobu.util.EntityUtils;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Transactional
    public OrderDTO placeOrder(ShoppingCartDTO shoppingCartDTO) throws TransactionException, ObjectTransferException {

        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO.Builder()
                .id(0)
                .date(new Date(System.currentTimeMillis()))
                .paymentMethod(Constants.CREDIT_CARD_PAYMENT_METHOD)
                .deliveryMethod(Constants.ONLINE_DELIVERY_METHOD)
                .create();
        OrderStatusDTO orderStatusDTO = orderStatusService.findByStatus(Constants.COMPLETE_ORDER_STATUS);
        Set<OrderItemDTO> orderItemDTOSet = shoppingCartItemDTOListToOrderItemDTOList(shoppingCartDTO.getShoppingCartItemDTOS());
        OrderDTO orderDTO = new OrderDTO.Builder()
                .id(0)
                .clientDTO(shoppingCartDTO.getClientDTO())
                .orderDetailsDTO(orderDetailsDTO)
                .orderStatusDTO(orderStatusDTO)
                .orderItemDTOS(orderItemDTOSet)
                .create();
        for(OrderItemDTO orderItemDTO: orderItemDTOSet){
            updateTicketStock(orderItemDTO);
            //orderItemService.save(orderItemDTO);
        }
        shoppingCartService.removeAllItems(shoppingCartDTO);
        return save(orderDTO);


    }

    private Set<OrderItemDTO> shoppingCartItemDTOListToOrderItemDTOList(Set<ShoppingCartItemDTO> shoppingCartItemDTOS) {
        Set<OrderItemDTO> orderItemDTOSet = new HashSet<>();
        for(ShoppingCartItemDTO shoppingCartItemDTO: shoppingCartItemDTOS){
            orderItemDTOSet.add(EntityUtils.shoppingCartItemDTOToOrderItemDTO(shoppingCartItemDTO));
        }
        return orderItemDTOSet;
    }

    public OrderDTO save(OrderDTO orderDTO){
        Order order = EntityUtils.orderDTOToOrder(orderDTO);
        //OrderDetails orderDetails = orderDetailsRepository.save(order.getOrderDetails());
        //order.setOrderDetails(orderDetails);
        Set<OrderItem> orderItemSet = order.getOrderItems();
        Set<OrderItem> savedOrderItemSet = new HashSet<>();
        order.setOrderItems(new HashSet<>());
        order = orderRepository.save(order);
        Integer orderId = order.getId();


        for(OrderItem orderItem: orderItemSet){
            orderItem.setIdOrder(orderId);
            savedOrderItemSet.add(orderItemRepository.save(orderItem));
        }
        order = orderRepository.findById(orderId).orElse(null);
        order.setOrderItems(savedOrderItemSet);
        return EntityUtils.orderToOrderDTO(order);
    }

    public void delete(OrderDTO orderDTO){
        Order order = EntityUtils.orderDTOToOrder(orderDTO);
        orderRepository.delete(order);
    }

    public List<OrderDTO> findByClient(ClientDTO clientDTO){
        Client client = EntityUtils.clientDTOToClient(clientDTO);
        List<Order> orderList = orderRepository.findByClient(client);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for(Order order: orderList){
            orderDTOList.add(EntityUtils.orderToOrderDTO(order));
        }
        return orderDTOList;
    }

    public OrderDTO findById(Integer orderId) throws ObjectTransferException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null){
            throw new ObjectTransferException("Data not found");

        }
        return EntityUtils.orderToOrderDTO(order);
    }

    private void updateTicketStock(OrderItemDTO orderItemDTO) throws TransactionException {

        TicketDTO ticketDTO = orderItemDTO.getTicketDTO();
        int initialQuantity = ticketDTO.getQuantity();
        int orderQuantity = orderItemDTO.getQuantity();
        int newQuantity = initialQuantity-orderQuantity;
        if(newQuantity < 0){
            throw new TransactionException("Not enough tickets in stock");
        }
        ticketDTO.setQuantity(newQuantity);
        ticketService.save(ticketDTO);
    }


}
