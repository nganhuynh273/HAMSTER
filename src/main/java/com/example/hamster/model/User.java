package com.example.hamster.model;

import javax.management.relation.Role;

public class User {
    private String name;
    private String password;
    private Role role;

    public User() {
    }
    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
    }
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }}
