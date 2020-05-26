package com.main.erobu.dto;


public class ShoppingCartItemDTO {

    private Integer id;
    private Integer shoppingCartId;
    private Integer quantity;
    private TicketDTO ticketDTO;

    public ShoppingCartItemDTO() {
    }

    public ShoppingCartItemDTO(Integer id, Integer shoppingCartId, Integer quantity, TicketDTO ticketDTO) {
        this.id = id;
        this.shoppingCartId = shoppingCartId;
        this.quantity = quantity;
        this.ticketDTO = ticketDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
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

    public String getEventName(){
        return ticketDTO.getEventDTO().getName();
    }

    public Double getTotalPrice(){
        return quantity * ticketDTO.getPrice();
    }

    public static class Builder {
        private Integer nestedId;
        private Integer nestedShoppingCartId;
        private Integer nestedQuantity;
        private TicketDTO nestedTicketDTO;

        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder shoppingCartId(Integer shoppingCartId) {
            this.nestedShoppingCartId = shoppingCartId;
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

        public ShoppingCartItemDTO create() {
            return new ShoppingCartItemDTO(nestedId, nestedShoppingCartId, nestedQuantity, nestedTicketDTO);
        }
    }
}
