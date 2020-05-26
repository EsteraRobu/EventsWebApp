package com.main.erobu.data.entry;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "editors")
public class Editor {

    @Id
    @Column(name = "id_editor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthDate")
    private Date birthDate;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Integer active;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "id_role")
    private Roles role;

    public Editor() {
    }

    public Editor(Integer id, String name, Date birthDate, String email, String username, String password, Integer active, String address, String phone, String description,Roles role) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.role=role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
    public static class Builder {
        private Integer nestedId;
        private String nestedName;
        private Date nestedBirthDate;
        private String nestedEmail;
        private String nestedUsername;
        private String nestedPassword;
        private Integer nestedActive;
        private String nestedAddress;
        private String nestedPhone;
        private String nestedDescription;
        private Roles nestedRole;


        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder name(String name) {
            this.nestedName = name;
            return this;
        }

        public Builder BirthDate(Date birthDate) {
            this.nestedBirthDate = birthDate;
            return this;
        }

        public Builder email(String email) {
            this.nestedEmail = email;
            return this;
        }

        public Builder username(String username) {
            this.nestedUsername = username;
            return this;
        }

        public Builder password(String password) {
            this.nestedPassword = password;
            return this;
        }

        public Builder active(Integer active) {
            this.nestedActive = active;
            return this;
        }

        public Builder address(String address) {
            this.nestedAddress = address;
            return this;
        }

        public Builder phone(String phone) {
            this.nestedPhone = phone;
            return this;
        }

        public Builder description(String description) {
            this.nestedDescription = description;
            return this;
        }


        public Builder role(Roles role) {
            this.nestedRole = role;
            return this;
        }

        public Editor create() {
            return new Editor(nestedId, nestedName, nestedBirthDate, nestedEmail, nestedUsername, nestedPassword, nestedActive, nestedAddress, nestedPhone, nestedDescription, nestedRole);
        }
    }
}


