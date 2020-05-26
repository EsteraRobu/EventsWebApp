package com.main.erobu.data.entry;

import javax.persistence.*;


@Entity(name = "ticket_category")
public class TicketCategory {

    @Id
    @Column(name = "id_ticket_category")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category")
    private String category;

    public TicketCategory() {
    }

    public TicketCategory(Integer id, String category) {
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

        public TicketCategory create() {
            return new TicketCategory(nestedId, nestedCategory);
        }

    }
}
