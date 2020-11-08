package ru.whereToEat.model;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class Meal  {
    private Integer id;

    private String description;

    private Float price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(String description, Float price) {
        this(null, description, price);
    }

    public Meal(Integer id, String description, Float price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public Meal(String description, Float price, Restaurant restaurant) {
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Meal(Integer id, String description, Float price, Restaurant restaurant) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", restaurant=" + restaurant +
                '}';
    }
}
