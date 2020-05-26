package com.main.erobu.data.entry;

import javax.persistence.*;


@Entity(name = "shopping_cart_item")
public class ShoppingCartItem {

    @Id
    @Column(name = "id_shopping_cart_item")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_shopping_cart")
    private Integer shoppingCartId;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "id_ticket")
    private Ticket ticket;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Integer id, Integer shoppingCartId, Integer quantity, Ticket ticket) {
        this.id = id;
        this.shoppingCartId = shoppingCartId;
        this.quantity = quantity;
        this.ticket = ticket;
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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public static class Builder {
        private Integer nestedId;
        private Integer nestedShoppingCartId;
        private Integer nestedQuantity;
        private Ticket nestedTicket;

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

        public Builder ticket(Ticket ticket) {
            this.nestedTicket = ticket;
            return this;
        }

        public ShoppingCartItem create() {
            return new ShoppingCartItem(nestedId, nestedShoppingCartId, nestedQuantity, nestedTicket);
        }
    }
}
