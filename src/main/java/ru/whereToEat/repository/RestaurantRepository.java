package ru.whereToEat.repository;

import ru.whereToEat.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    // null if not found, when updated
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int restaurantId);

    // null if not found
    // return without meals
    Restaurant get(int restaurantId);

    // empty list if not found
    List<Restaurant> getAll();




}
