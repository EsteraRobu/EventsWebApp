package com.main.erobu.dto;

import java.sql.Date;

public class OrderDetailsDTO {

    private Integer id;
    private Date date;
    private String paymentMethod;
    private String deliveryMethod;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(Integer id, Date date, String paymentMethod, String deliveryMethod) {
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

        public OrderDetailsDTO create() {
            return new OrderDetailsDTO(nestedId, nestedDate, nestedPaymentMethod, nestedDeliveryMethod);
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
        OrderDetailsDTO orderDetailsDTO = (OrderDetailsDTO) obj;
        // field comparison
        return this.id.equals(orderDetailsDTO.getId()) &&
                this.date.toString().equals(orderDetailsDTO.getDate().toString()) &&
                this.paymentMethod.equals(orderDetailsDTO.getPaymentMethod()) &&
                this.deliveryMethod.equals(orderDetailsDTO.getDeliveryMethod());
    }
}
