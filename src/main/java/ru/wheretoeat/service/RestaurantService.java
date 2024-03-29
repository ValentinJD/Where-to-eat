package ru.wheretoeat.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.wheretoeat.model.Meal;
import ru.wheretoeat.model.Restaurant;
import ru.wheretoeat.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ru.wheretoeat.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MealService mealService;
    private final VoteService voteService;

    public RestaurantService(RestaurantRepository restaurantRepository, MealService mealService, VoteService voteService) {
        this.restaurantRepository = restaurantRepository;
        this.mealService = mealService;

        this.voteService = voteService;
    }

    public Restaurant get(int restaurantId) {
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        checkNotFoundWithId(restaurant, restaurantId);
        return restaurant;
    }

    public List<Integer> getAllRestaurantId() {
        List<Restaurant> list = restaurantRepository.getAll();
        List<Integer> listId = new ArrayList<>();
        list.forEach((restaurant) -> {
            listId.add(restaurant.getId());
        });

        list.sort(Comparator.comparing(Restaurant::getId));
        return listId;
    }


    public Restaurant getWithMeals(int restaurantId) {
        checkNotFoundWithId(get(restaurantId), restaurantId);
        return restaurantRepository.getWithMeals(restaurantId);
    }

    //@CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int restaurantId) {
        checkNotFoundWithId(restaurantRepository.delete(restaurantId), restaurantId);
    }

    //@CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }


    private List<Meal> getMeals(int restaurantId) {
        return mealService.getAll(restaurantId);
    }

    private int getCountVote(int restaurantId) {
        return voteService.getCountVote(restaurantId);
    }

    //@Cacheable("restaurants")
    public List<Restaurant> getAll() {
        List<Restaurant> list = restaurantRepository.getAll();

        list.forEach((restaurant) -> {
            restaurant.setMenu(getMeals(restaurant.getId()));
            restaurant.setVote_count(getCountVote(restaurant.getId()));
        });

        list.sort(Comparator.comparing(Restaurant::getId));
        return list;
    }

    //@CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }
}
