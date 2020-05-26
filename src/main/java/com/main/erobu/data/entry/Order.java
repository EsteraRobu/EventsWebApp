package com.main.erobu.data.entry;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "orders")
public class Order {

    @Id
    @Column(name = "id_order")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_order_details")
    private OrderDetails orderDetails;

    @ManyToOne
    @JoinColumn(name = "id_order_status")
    private OrderStatus orderStatus;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_order", referencedColumnName = "id_order")
    private Set<OrderItem> orderItems;

    public Order() {
    }

    public Order(Integer id, Client client, OrderDetails orderDetails, OrderStatus orderStatus, Set<OrderItem> orderItems) {
        this.id = id;
        this.client = client;
        this.orderDetails = orderDetails;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
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

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public static class Builder {
        private Integer nestedId;
        private Client nestedClient;
        private OrderDetails nestedOrderDetails;
        private OrderStatus nestedOrderStatus;
        private Set<OrderItem> nestedOrderItems;

        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder client(Client client) {
            this.nestedClient = client;
            return this;
        }

        public Builder orderDetails(OrderDetails orderDetails) {
            this.nestedOrderDetails = orderDetails;
            return this;
        }

        public Builder orderStatus(OrderStatus orderStatus) {
            this.nestedOrderStatus = orderStatus;
            return this;
        }

        public Builder orderItems(Set<OrderItem> orderItems) {
            this.nestedOrderItems = orderItems;
            return this;
        }

        public Order create() {
            return new Order(nestedId, nestedClient, nestedOrderDetails, nestedOrderStatus, nestedOrderItems);
        }
    }
}
