package ru.whereToEat.model;

import java.time.LocalDateTime;


public class User extends AbstractNamedEntity {

    private String email;

    private String password;

    private boolean enabled = true;

    private LocalDateTime registered;

    private Role role;


    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRegistered(), u.getRole());
    }

    public User() {
    }

    public User(Integer userId, String name, String email, String password, boolean enabled, LocalDateTime registered, Role role) {
        super(userId, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.role = role;

    }

    public User(String name, String email, String password, boolean enabled, LocalDateTime registered, Role role) {
        super(null, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.role = role;
    }

    public User(String name, String email, String password, Role role) {
        super(null, name);
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(Integer id, String name, String email, String password, Role user) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.role = user;
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

    public LocalDateTime getRegistered() {
        return registered;
    }

    public Role getRole() {
        return role;
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

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}