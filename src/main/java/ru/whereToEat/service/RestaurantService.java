package ru.whereToEat.service;

import ru.whereToEat.model.Restaurant;
import ru.whereToEat.repository.RestaurantRepository;

import java.util.List;

public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> getall() {
        return repository.getAll();
    }

    public Restaurant get(int restaurantId) {
        return repository.get(restaurantId);
    }

    public void delete(int restaurantId) {
        repository.delete(restaurantId);
    }

    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        return repository.save(restaurant);
    }
}
