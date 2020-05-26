package com.main.erobu.dto;


public class TicketDTO {


    private Integer id;
    private EventDTO eventDTO;
    private TicketCategoryDTO ticketCategoryDTO;
    private Integer quantity;
    private Double price;


    public TicketDTO() {
    }

    public TicketDTO(Integer id, EventDTO eventDTO, TicketCategoryDTO ticketCategoryDTO, Integer quantity, Double price) {
        this.id = id;
        this.eventDTO = eventDTO;
        this.ticketCategoryDTO = ticketCategoryDTO;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EventDTO getEventDTO() {
        return eventDTO;
    }

    public void setEventDTO(EventDTO eventDTO) {
        this.eventDTO = eventDTO;
    }

    public TicketCategoryDTO getTicketCategoryDTO() {
        return ticketCategoryDTO;
    }

    public void setTicketCategoryDTO(TicketCategoryDTO ticketCategoryDTO) {
        this.ticketCategoryDTO = ticketCategoryDTO;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public static class Builder {
        private Integer nestedId;
        private EventDTO nestedEventDTO;
        private TicketCategoryDTO nestedTicketCategoryDTO;
        private Integer nestedQuantity;
        private Double nestedPrice;

        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder eventDTO(EventDTO eventDTO) {
            this.nestedEventDTO = eventDTO;
            return this;
        }

        public Builder ticketCategoryDTO(TicketCategoryDTO ticketCategoryDTO) {
            this.nestedTicketCategoryDTO = ticketCategoryDTO;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.nestedQuantity = quantity;
            return this;
        }

        public Builder price(Double price) {
            this.nestedPrice = price;
            return this;
        }

        public TicketDTO create() {
            return new TicketDTO(nestedId, nestedEventDTO, nestedTicketCategoryDTO, nestedQuantity, nestedPrice);
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
        TicketDTO ticketDTO = (TicketDTO) obj;
        // field comparison
        return this.id.equals(ticketDTO.getId()) &&
                this.eventDTO.equals(ticketDTO.getEventDTO()) &&
                this.ticketCategoryDTO.equals(ticketDTO.getTicketCategoryDTO()) &&
                this.quantity.equals(ticketDTO.getQuantity()) &&
                this.price.equals(ticketDTO.getPrice());
    }
}

