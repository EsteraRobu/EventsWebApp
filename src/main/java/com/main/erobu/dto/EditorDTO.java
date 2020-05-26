package com.main.erobu.dto;

import java.sql.Date;


public class EditorDTO {


    private Integer id;
    private String name;
    private Date birthDate;
    private String email;
    private String username;
    private String password;
    private Integer active;
    private String address;
    private String phone;
    private String description;

    public EditorDTO() {
        this.active = 0;

    }

    public EditorDTO(Integer id, String name, Date birthDate, String email, String username, String password, Integer active, String address, String phone, String description) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


        public Builder id(Integer id) {
            this.nestedId = id;
            return this;
        }

        public Builder name(String name) {
            this.nestedName = name;
            return this;
        }

        public Builder birthDate(Date birthDate) {
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



        public EditorDTO create() {
            return new EditorDTO(nestedId, nestedName, nestedBirthDate, nestedEmail, nestedUsername, nestedPassword, nestedActive, nestedAddress, nestedPhone, nestedDescription);
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
        EditorDTO editorDTO = (EditorDTO) obj;
        // field comparison
        return this.id.equals(editorDTO.getId()) &&
                this.name.equals(editorDTO.getName()) &&
                this.birthDate.equals(editorDTO.getBirthDate()) &&
                this.email.equals(editorDTO.getEmail()) &&
                this.username.equals(editorDTO.getUsername()) &&
                this.password.equals(editorDTO.getPassword()) &&
                this.active.equals(editorDTO.getActive()) &&
                this.address.equals(editorDTO.getAddress()) &&
                this.phone.equals(editorDTO.getPhone()) &&
                this.description.equals(editorDTO.getDescription()) ;
    }
}
