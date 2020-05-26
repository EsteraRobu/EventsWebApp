package com.main.erobu.data.entry;

import javax.persistence.*;

@Entity(name = "roles")
public class Roles {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role")
    private String role;

    public Roles() {
    }

    public Roles(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String name) {
        this.role = role;
    }
    public static class Builder {
        private Integer nestedId;
        private String nestedRole;


        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder name(String role) {
            this.nestedRole = role;
            return this;
        }

        public Roles create() {
            return new Roles(nestedId, nestedRole);
        }
    }
}
