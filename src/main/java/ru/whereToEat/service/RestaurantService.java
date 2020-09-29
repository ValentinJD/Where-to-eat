package ru.whereToEat.service;

import org.springframework.stereotype.Service;
import ru.whereToEat.model.CountVote;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.repository.CountVoteRepository;
import ru.whereToEat.repository.MealRepository;
import ru.whereToEat.repository.RestaurantRepository;
import ru.whereToEat.to.RestaurantTO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MealRepository mealRepository;
    private final CountVoteRepository countVoteRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MealRepository mealRepository, CountVoteRepository countVoteRepository) {
        this.restaurantRepository = restaurantRepository;
        this.mealRepository = mealRepository;
        this.countVoteRepository = countVoteRepository;
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

    private List<Meal> getMeals(int restaurantId) {
        return mealRepository.getAll(restaurantId);
    }

    private CountVote getCountVote(int restaurantId) {
        return countVoteRepository.get(restaurantId);
    }

    public List<RestaurantTO> getAllTO() {
        return restaurantRepository.getAll().stream()
                .map((restaurant) -> {
                    RestaurantTO restaurantTO = new RestaurantTO("");
                    restaurantTO.setId(restaurant.getId());
                    restaurantTO.setName(restaurant.getName());
                    restaurantTO.setMenu(getMeals(restaurant.getId()));
                    restaurantTO.setVote_count(getCountVote(restaurant.getId()).getCount());
                    return restaurantTO;
                })
                .sorted(Comparator.comparing(RestaurantTO::getId))
                .collect(Collectors.toList());
    }
}
