package ru.whereToEat.repository;

import ru.whereToEat.model.Restaurant;
import ru.whereToEat.model.User;

import java.util.List;

public interface RestaurantRepository {
    // null if not found, when updated
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // NotFoundException if not found
    Restaurant get(int id);

    List<Restaurant> getAll();
}
