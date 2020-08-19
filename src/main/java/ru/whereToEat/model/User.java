package ru.whereToEat.model;

import java.util.Date;


public class User {

    private Integer userId;

    private String name;

    private String email;

    private String password;

    private boolean enabled = true;

    private Date registered = new Date();

    private Role role;

    public User() {
    }

    public User(Integer userId, String name, String email, String password, boolean enabled, Date registered, Role role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.role = role;
    }

    public User(String name, String email, String password, boolean enabled, Date registered, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.role = role;
    }

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public Role getRole() {
        return role;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isNew(){
        return userId == null;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", roles=" + role +
                '}';
    }
}