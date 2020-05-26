package com.main.erobu.dto;


public class OrderStatusDTO {


    private Integer id;
    private String status;

    public OrderStatusDTO() {
    }

    public OrderStatusDTO(Integer id, String status) {
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

        public OrderStatusDTO create() {
            return new OrderStatusDTO(nestedId, nestedStatus);
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
        OrderStatusDTO orderStatusDTO = (OrderStatusDTO) obj;
        // field comparison
        return this.id.equals(orderStatusDTO.getId()) &&
                this.status.equals(orderStatusDTO.getStatus());
    }
}
