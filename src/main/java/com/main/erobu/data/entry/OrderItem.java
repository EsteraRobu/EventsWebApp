package com.main.erobu.data.entry;

import javax.persistence.*;


@Entity(name = "order_item")
public class OrderItem {

    @Id
    @Column(name = "id_order_item")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_order")
    private Integer idOrder;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "id_ticket")
    private Ticket ticket;

    public OrderItem() {
    }

    public OrderItem(Integer id, Integer idOrder, Integer quantity, Ticket ticket) {
        this.id = id;
        this.idOrder = idOrder;
        this.quantity = quantity;
        this.ticket = ticket;
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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public static class Builder {
        private Integer nestedId;
        private Integer nestedIdOrder;
        private Integer nestedQuantity;
        private Ticket nestedTicket;

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

        public Builder ticket(Ticket ticket) {
            this.nestedTicket = ticket;
            return this;
        }

        public OrderItem create() {
            return new OrderItem(nestedId, nestedIdOrder, nestedQuantity, nestedTicket);
        }
    }
}
