package com.main.erobu.data.entry;

import javax.persistence.*;


@Entity(name = "order_status")
public class OrderStatus {

    @Id
    @Column(name = "id_order_status")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    private String status;

    public OrderStatus() {
    }

    public OrderStatus(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class Builder {
        private Integer nestedId;
        private String nestedStatus;

        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder status(String status) {
            this.nestedStatus = status;
            return this;
        }

        public OrderStatus create() {
            return new OrderStatus(nestedId, nestedStatus);
        }
    }
}
