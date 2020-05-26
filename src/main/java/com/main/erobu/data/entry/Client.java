package com.main.erobu.data.entry;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity(name = "clients")
public class Client {

    @Id
    @Column(name = "id_client")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
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


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "client_event_category",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id_client"),
            inverseJoinColumns = @JoinColumn(name = "eventCategory_id", referencedColumnName = "id_event_category"))
    private Set<EventCategory> eventcategories;

    @Column(name = "phone")
    private String phone;


    @OneToOne
    @JoinColumn(name = "id_role")
    private Roles role;

    public Client() {
    }

    public Client(Integer id, String firstName, String lastName, Date birthDate, String email, String username, String password, Integer active, String address, String phone, Roles role, Set<EventCategory> eventCategories) {
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
        this.role = role;
        this.eventcategories = eventCategories;
        if (Objects.nonNull(this.getEventcategories())) {
            this.eventcategories.forEach(x -> x.getClients().add(this));
        }
    }

    public Set<EventCategory> getEventcategories() {
        return eventcategories;
    }

    public void setEventcategories(Set<EventCategory> eventcategories) {
        Set<EventCategory> eventFavCategories = new HashSet<>();
        if(Objects.nonNull(this.getEventcategories())){
        eventFavCategories = this.getEventcategories();
        for (EventCategory eventCategory : eventcategories) {
            if (!eventFavCategories.contains(eventCategory)) {
                eventFavCategories.add(eventCategory);
            }
        }} else eventFavCategories=eventcategories;
        this.eventcategories = eventFavCategories;
        this.eventcategories.forEach(x -> x.getClients().add(this));
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


    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
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

        private Roles nestedRole;
        private Set<EventCategory> nestedEventCategories;

        public Builder eventCategories(Set<EventCategory> eventCategories) {
            this.nestedEventCategories = eventCategories;
            // this.nestedEventCategories.forEach(x -> x.getClients().add(this));
            return this;
        }

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


        public Builder role(Roles role) {
            this.nestedRole = role;
            return this;
        }

        public Client create() {
            return new Client(nestedId, nestedFirstName, nestedLastName, nestedBirthDate, nestedEmail, nestedUsername, nestedPassword, nestedActive, nestedAddress, nestedPhone, nestedRole, nestedEventCategories);
        }
    }
}
