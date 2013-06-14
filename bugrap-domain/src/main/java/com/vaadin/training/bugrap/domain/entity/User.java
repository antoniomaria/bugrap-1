package com.vaadin.training.bugrap.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "BUGRAP_USER")
@NamedQuery(name = User.FIND_BY_USERNAME, query = "select u from User u where u.username = :username")
public class User extends AbstractEntity {

    public static final String FIND_BY_USERNAME = "user.findByUsername";

    @Column(nullable = false, unique = true)
    private String username;

    private String name;
    private String email;
    private String password;


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return name;
    }
}
