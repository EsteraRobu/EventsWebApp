package com.main.erobu.entity;

import com.main.erobu.dto.TicketDTO;


public class CartItem {

    private TicketDTO ticketDTO;
    private Integer quantity;

    public CartItem() {
    }

    public CartItem(TicketDTO ticketDTO, Integer quantity) {
        this.ticketDTO = ticketDTO;
        this.quantity = quantity;
    }

    public TicketDTO getTicketDTO() {
        return ticketDTO;
    }

    public void setTicketDTO(TicketDTO ticketDTO) {
        this.ticketDTO = ticketDTO;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
