package ru.whereToEat.model;

import java.time.LocalDateTime;

public class Vote {

    private Integer id;

    private int userId;

    private LocalDateTime date_vote;

    private int restaurantId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate_vote() {
        return date_vote;
    }

    public void setDate_vote(LocalDateTime date_vote) {
        this.date_vote = date_vote;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", userId=" + userId +
                ", date_vote=" + date_vote +
                ", restaurantId=" + restaurantId +
                ", vote=" + vote +
                '}';
    }
}
