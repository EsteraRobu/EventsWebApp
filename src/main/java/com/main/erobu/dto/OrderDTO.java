package com.main.erobu.dto;

import java.util.Set;


public class OrderDTO {

    private Integer id;
    private ClientDTO clientDTO;
    private OrderDetailsDTO orderDetailsDTO;
    private OrderStatusDTO orderStatusDTO;
    private Set<OrderItemDTO> orderItemDTOS;

    public OrderDTO() {
    }

    public OrderDTO(Integer id, ClientDTO clientDTO, OrderDetailsDTO orderDetailsDTO, OrderStatusDTO orderStatusDTO, Set<OrderItemDTO> orderItemDTOS) {
        this.id = id;
        this.clientDTO = clientDTO;
        this.orderDetailsDTO = orderDetailsDTO;
        this.orderStatusDTO = orderStatusDTO;
        this.orderItemDTOS = orderItemDTOS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public OrderDetailsDTO getOrderDetailsDTO() {
        return orderDetailsDTO;
    }

    public void setOrderDetailsDTO(OrderDetailsDTO orderDetailsDTO) {
        this.orderDetailsDTO = orderDetailsDTO;
    }

    public OrderStatusDTO getOrderStatusDTO() {
        return orderStatusDTO;
    }

    public void setOrderStatusDTO(OrderStatusDTO orderStatusDTO) {
        this.orderStatusDTO = orderStatusDTO;
    }

    public Set<OrderItemDTO> getOrderItemDTOS() {
        return orderItemDTOS;
    }

    public void setOrderItemDTOS(Set<OrderItemDTO> orderItemDTOS) {
        this.orderItemDTOS = orderItemDTOS;
    }

    public Double getTotalPrice() {
        double totalPrice = 0.f;
        for (OrderItemDTO orderItemDTO : orderItemDTOS) {
            totalPrice += orderItemDTO.getTotalPrice();
        }
        return totalPrice;
    }


    public static class Builder {
        private Integer nestedId;
        private ClientDTO nestedClientDTO;
        private OrderDetailsDTO nestedOrderDetailsDTO;
        private OrderStatusDTO nestedOrderStatusDTO;
        private Set<OrderItemDTO> nestedOrderItemDTOS;

        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder clientDTO(ClientDTO clientDTO) {
            this.nestedClientDTO = clientDTO;
            return this;
        }

        public Builder orderDetailsDTO(OrderDetailsDTO orderDetailsDTO) {
            this.nestedOrderDetailsDTO = orderDetailsDTO;
            return this;
        }

        public Builder orderStatusDTO(OrderStatusDTO orderStatusDTO) {
            this.nestedOrderStatusDTO = orderStatusDTO;
            return this;
        }

        public Builder orderItemDTOS(Set<OrderItemDTO> orderItemDTOS) {
            this.nestedOrderItemDTOS = orderItemDTOS;
            return this;
        }

        public OrderDTO create() {
            return new OrderDTO(nestedId, nestedClientDTO, nestedOrderDetailsDTO, nestedOrderStatusDTO, nestedOrderItemDTOS);
        }
    }

    @Override
    public boolean equals(Object obj) {
        // self check
        if (this == obj)
            return true;
        // null check
        if (obj == null)
            return false;
        // type check and cast
        if (getClass() != obj.getClass())
            return false;
        OrderDTO orderDTO = (OrderDTO) obj;
        // field comparison
        return this.id.equals(orderDTO.getId()) &&
                this.clientDTO.equals(orderDTO.getClientDTO()) &&
                this.orderDetailsDTO.equals(orderDTO.getOrderDetailsDTO()) &&
                this.orderStatusDTO.equals(orderDTO.getOrderStatusDTO());
    }
}
