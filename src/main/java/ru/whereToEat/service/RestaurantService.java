package ru.whereToEat.service;

import org.springframework.stereotype.Service;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.repository.MealRepository;
import ru.whereToEat.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MealRepository mealRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MealRepository mealRepository) {
        this.restaurantRepository = restaurantRepository;
        this.mealRepository = mealRepository;
    }

    public List<Restaurant> getAll() {
        List<Restaurant> restaurants = restaurantRepository.getAll();
        restaurants.sort(new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant o1, Restaurant o2) {
                return o1.getRestaraunt_Id().compareTo(o2.getRestaraunt_Id());
            }
        });
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
