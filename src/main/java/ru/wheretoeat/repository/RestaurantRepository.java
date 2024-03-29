package ru.wheretoeat.repository;

import ru.wheretoeat.model.Restaurant;

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
    //ordered by restaurantId
    List<Restaurant> getAll();

    default Restaurant getWithMeals(int restaurantId) {
        throw new UnsupportedOperationException();
    }


}
