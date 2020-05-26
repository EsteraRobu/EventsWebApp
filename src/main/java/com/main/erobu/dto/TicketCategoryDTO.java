package com.main.erobu.dto;


public class TicketCategoryDTO {


    private Integer id;
    private String category;

    public TicketCategoryDTO() {
    }

    public TicketCategoryDTO(Integer id, String category) {
        this.id = id;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static class Builder {
        private Integer nestedId;
        private String nestedCategory;

        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder category(String category) {
            this.nestedCategory = category;
            return this;
        }

        public TicketCategoryDTO create() {
            return new TicketCategoryDTO(nestedId, nestedCategory);
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
        TicketCategoryDTO ticketCategoryDTO = (TicketCategoryDTO) obj;
        // field comparison
        return this.id.equals(ticketCategoryDTO.getId()) &&
                this.category.equals(ticketCategoryDTO.getCategory()) ;
    }
}
