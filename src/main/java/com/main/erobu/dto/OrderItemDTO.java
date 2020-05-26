package com.main.erobu.dto;


public class OrderItemDTO {


    private Integer id;
    private Integer idOrder;
    private Integer quantity;
    private TicketDTO ticketDTO;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Integer id, Integer idOrder, Integer quantity, TicketDTO ticketDTO) {
        this.id = id;
        this.idOrder = idOrder;
        this.quantity = quantity;
        this.ticketDTO = ticketDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public TicketDTO getTicketDTO() {
        return ticketDTO;
    }

    public void setTicketDTO(TicketDTO ticketDTO) {
        this.ticketDTO = ticketDTO;
    }

    public Double getTotalPrice() {
        return quantity * ticketDTO.getPrice();
    }

    public String getEventName() {
        return ticketDTO.getEventDTO().getName();
    }

    public String getEventLocation() {
        return ticketDTO.getEventDTO().getLocation();

    }

    public Integer getEventId() {
        return ticketDTO.getEventDTO().getId();

    }

    public static class Builder {
        private Integer nestedId;
        private Integer nestedIdOrder;
        private Integer nestedQuantity;
        private TicketDTO nestedTicketDTO;

        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder idOrder(Integer idOrder) {
            this.nestedIdOrder = idOrder;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.nestedQuantity = quantity;
            return this;
        }

        public Builder ticketDTO(TicketDTO ticketDTO) {
            this.nestedTicketDTO = ticketDTO;
            return this;
        }

        public OrderItemDTO create() {
            return new OrderItemDTO(nestedId, nestedIdOrder, nestedQuantity, nestedTicketDTO);
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
        OrderItemDTO orderItemDTO = (OrderItemDTO) obj;
        // field comparison
        return this.id.equals(orderItemDTO.getId()) &&
                this.idOrder.equals(orderItemDTO.getIdOrder()) &&
                this.quantity.equals(orderItemDTO.getQuantity()) &&
                this.ticketDTO.equals(orderItemDTO.getTicketDTO());
    }
}
