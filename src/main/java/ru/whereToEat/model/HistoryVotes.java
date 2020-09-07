package ru.whereToEat.model;

import java.time.LocalDateTime;

public class HistoryVotes {

    private Integer id;

    private User user;

    private LocalDateTime date_vote;

    private Restaurant restaurant;

    public HistoryVotes() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate_vote() {
        return date_vote;
    }

    public void setDate_vote(LocalDateTime date_vote) {
        this.date_vote = date_vote;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
