package com.main.erobu.data.entry;

import javax.persistence.*;
import java.sql.Date;


@Entity(name = "order_details")
public class OrderDetails {

    @Id
    @Column(name = "id_order_details")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private Date date;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "delivery_method")
    private String deliveryMethod;

    public OrderDetails() {
    }

    public OrderDetails(Integer id, Date date, String paymentMethod, String deliveryMethod) {
        this.id = id;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public static class Builder {
        private Integer nestedId;
        private Date nestedDate;
        private String nestedPaymentMethod;
        private String nestedDeliveryMethod;

        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder date(Date date) {
            this.nestedDate = date;
            return this;
        }

        public Builder paymentMethod(String paymentMethod) {
            this.nestedPaymentMethod = paymentMethod;
            return this;
        }

        public Builder deliveryMethod(String deliveryMethod) {
            this.nestedDeliveryMethod = deliveryMethod;
            return this;
        }

        public OrderDetails create() {
            return new OrderDetails(nestedId, nestedDate, nestedPaymentMethod, nestedDeliveryMethod);
        }
    }
}
