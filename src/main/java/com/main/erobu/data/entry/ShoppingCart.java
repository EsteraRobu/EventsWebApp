package com.main.erobu.data.entry;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @Column(name = "id_shopping_cart")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_shopping_cart", referencedColumnName = "id_shopping_cart")
    private Set<ShoppingCartItem> shoppingCartItems;

    public ShoppingCart() {
    }

    public ShoppingCart(Integer id, Client client, Set<ShoppingCartItem> shoppingCartItems) {
        this.id = id;
        this.client = client;
        this.shoppingCartItems = shoppingCartItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(Set<ShoppingCartItem> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }

    public static class Builder {
        private Integer nestedId;
        private Client nestedClient;
        private Set<ShoppingCartItem> nestedShoppingCartItems;

        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder client(Client client) {
            this.nestedClient = client;
            return this;
        }

        public Builder shoppingCartItems(Set<ShoppingCartItem> shoppingCartItems) {
            this.nestedShoppingCartItems = shoppingCartItems;
            return this;
        }

        public ShoppingCart create() {
            return new ShoppingCart(nestedId, nestedClient, nestedShoppingCartItems);
        }
    }
}
