package ru.whereToEat.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.repository.MealRepository;
import ru.whereToEat.repository.RestaurantRepository;

import java.util.Comparator;
import java.util.List;

import static ru.whereToEat.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MealRepository mealRepository;
    private final VoteService voteService;

    public RestaurantService(RestaurantRepository restaurantRepository, MealRepository mealRepository, VoteService voteService) {
        this.restaurantRepository = restaurantRepository;
        this.mealRepository = mealRepository;

        this.voteService = voteService;
    }

    public Restaurant get(int restaurantId) {
        return restaurantRepository.get(restaurantId);
    }

    public void delete(int restaurantId) {
        checkNotFoundWithId(restaurantRepository.delete(restaurantId), restaurantId);
    }

    public Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    private List<Meal> getMeals(int restaurantId) {
        return mealRepository.getAll(restaurantId);
    }

    private int getCountVote(int restaurantId) {
        return voteService.getCountVote(restaurantId);
    }

    public List<Restaurant> getAll() {
        List<Restaurant> list = restaurantRepository.getAll();

        list.forEach((restaurant) -> {
            restaurant.setMenu(getMeals(restaurant.getId()));
            restaurant.setVote_count(getCountVote(restaurant.getId()));
        });

        list.sort(Comparator.comparing(Restaurant::getId));
        return list;
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }
}
