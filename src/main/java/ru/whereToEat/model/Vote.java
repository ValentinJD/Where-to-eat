package ru.whereToEat.model;

import java.time.LocalDateTime;

public class Vote {

    private Integer id;

    private User user;

    private LocalDateTime date_vote;

    private Restaurant restaurant;

    private int vote;

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public Vote() {
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

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", user=" + user +
                ", date_vote=" + date_vote +
                ", restaurant=" + restaurant +
                ", vote=" + vote +
                '}';
    }
}
