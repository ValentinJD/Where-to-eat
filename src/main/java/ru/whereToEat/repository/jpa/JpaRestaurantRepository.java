package ru.whereToEat.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.repository.RestaurantRepository;

import java.util.List;

@Repository
public class JpaRestaurantRepository implements RestaurantRepository {

    @Override
    public Restaurant save(Restaurant restaurant) {
        return null;
    }

    @Override
    public boolean delete(int restaurantId) {
        return false;
    }

    @Override
    public Restaurant get(int restaurantId) {
        return null;
    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }
}