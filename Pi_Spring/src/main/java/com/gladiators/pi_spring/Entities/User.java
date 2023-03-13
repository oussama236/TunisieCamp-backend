package com.gladiators.pi_spring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class User {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String picture;

    @Column
    private String favouriteCenter;

    @Column
    private Integer phoneNumber;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "users")
    @JsonIgnore
    private List<Role> roles ;

    public User(Long id, String firstName, String lastName, String login, String password, String email, String picture, String favouriteCenter, Integer phoneNumber, List<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.picture = picture;
        this.favouriteCenter = favouriteCenter;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    public User(String firstName, String lastName, String login, String password, String email, String picture, String favouriteCenter, Integer phoneNumber, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.picture = picture;
        this.favouriteCenter = favouriteCenter;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    public User() {

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setFavouriteCenter(String favouriteCenter) {
        this.favouriteCenter = favouriteCenter;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }

    public String getFavouriteCenter() {
        return favouriteCenter;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", picture='" + picture + '\'' +
                ", favouriteCenter='" + favouriteCenter + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", roles=" + roles +
                '}';
    }
}