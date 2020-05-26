package com.main.erobu.data.entry;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "event_category")
public class EventCategory {

    @Id
    @Column(name = "id_event_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category")
    private String category;

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @ManyToMany(mappedBy = "eventcategories")
    private Set<Client> clients = new HashSet<>();


    public EventCategory() {
    }

    public EventCategory(Integer id, String category, Set<Client>  idsClient) {
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
        private Set<Client> nestedIdsClient;
        public Builder idsClient (Set<Client> idsClient) {
            this.nestedIdsClient = idsClient;
            return this;
        }
        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder category(String category) {
            this.nestedCategory = category;
            return this;
        }

        public EventCategory create() {
            return new EventCategory(nestedId, nestedCategory,nestedIdsClient);
        }
    }
}
