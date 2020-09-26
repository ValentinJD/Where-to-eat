package ru.whereToEat.service;

import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.repository.MealRepository;
import ru.whereToEat.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MealRepository mealRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MealRepository mealRepository) {
        this.restaurantRepository = restaurantRepository;
        this.mealRepository = mealRepository;
    }

    public List<Restaurant> getAll() {
        List<Restaurant> restaurants = restaurantRepository.getAll();
        return setMealsForListRestaurants(restaurants);
    }

    public Restaurant get(int restaurantId) {
        return restaurantRepository.get(restaurantId);
    }

    public void delete(int restaurantId) {
        restaurantRepository.delete(restaurantId);
    }

    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant getWithMeals(int restaurantId) {
        Restaurant restaurant = new Restaurant();
        List<Meal> list = mealRepository.getAll(restaurantId);
        restaurant.setMenu(list);
        return restaurant;
    }

    public List<Meal> getMeals(int restaurantId) {
        return mealRepository.getAll(restaurantId);
    }

    private List<Restaurant> setMealsForListRestaurants(List<Restaurant> restaurants) {
        List<Restaurant> restaurantsWithMeals = new ArrayList<>();
        restaurants.forEach(
                restaurant -> {
                    restaurant.setMenu(getMeals(restaurant.getRestaraunt_Id()));
                    restaurantsWithMeals.add(restaurant);
                }
        );
        return restaurantsWithMeals;
    }
}
