package com.main.erobu.dto;

import java.sql.Date;


public class AdministratorDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String username;
    private String password;
    private Integer active;
    private String address;
    private String phone;
private Integer roleId;
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


    public AdministratorDTO() {
    }

    public AdministratorDTO(Integer id, String firstName, String lastName, Date birthDate, String email, String username, String password, Integer active, String address, String phone,Integer roleId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;
        this.address = address;
        this.phone = phone;
        this.roleId=roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public static class Builder {
        private Integer nestedId;
        private String nestedFirstName;
        private String nestedLastName;
        private Date nestedBirthDate;
        private String nestedEmail;
        private String nestedUsername;
        private String nestedPassword;
        private Integer nestedActive;
        private String nestedAddress;
        private String nestedPhone;
        private Integer nestedRoleId;

        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.nestedFirstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.nestedLastName = lastName;
            return this;
        }

        public Builder birthDate(Date nestedBirthDate) {
            this.nestedBirthDate = nestedBirthDate;
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
        public Builder roleId(Integer nestedRoleId) {
            this.nestedRoleId = nestedRoleId;
            return this;
        }
        public AdministratorDTO create() {
            return new AdministratorDTO(nestedId, nestedFirstName, nestedLastName, nestedBirthDate, nestedEmail, nestedUsername, nestedPassword, nestedActive, nestedAddress, nestedPhone,nestedRoleId);
        }
    }
}
