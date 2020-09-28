package ru.whereToEat.model;

import java.time.LocalDate;

public class CountVote extends AbstractBaseEntity {

    LocalDate date;

    int restaurantId;

    int count;

    public CountVote() {
        super(null);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CountVote{" +
                "date=" + date +
                ", restaurantId=" + restaurantId +
                ", count=" + count +
                '}';
    }
}
