package com.main.erobu.data.entry;

import javax.persistence.*;


@Entity(name = "tickets")
public class Ticket {

    @Id
    @Column(name = "id_ticket")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_ticket_category")
    private TicketCategory ticketCategory;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;


    public Ticket() {
    }

    public Ticket(Integer id, Event event, TicketCategory ticketCategory, Integer quantity, Double price) {
        this.id = id;
        this.event = event;
        this.ticketCategory = ticketCategory;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
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
        private Event nestedEvent;
        private TicketCategory nestedTicketCategory;
        private Integer nestedQuantity;
        private Double nestedPrice;

        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder event(Event event) {
            this.nestedEvent = event;
            return this;
        }

        public Builder ticketCategory(TicketCategory ticketCategory) {
            this.nestedTicketCategory = ticketCategory;
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

        public Ticket create() {
            return new Ticket(nestedId, nestedEvent, nestedTicketCategory, nestedQuantity, nestedPrice);
        }

    }
}

