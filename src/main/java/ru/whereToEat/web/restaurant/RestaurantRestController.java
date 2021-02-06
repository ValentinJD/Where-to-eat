package ru.whereToEat.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.whereToEat.exceptions.NotEnoughRightsException;
import ru.whereToEat.exceptions.NotVoteException;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.model.Vote;
import ru.whereToEat.service.RestaurantService;
import ru.whereToEat.web.SecurityUtil;
import ru.whereToEat.web.meal.MealRestController;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL_RESTAURANT, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController extends AbstractRestaurantController {
    public static final String REST_URL_RESTAURANT = "/rest";

    @GetMapping(value = "/restaurant/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable int restaurantId) {
        return super.get(restaurantId);
    }

    @GetMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/restaurants/filter/{nameRestaurant}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getFilteredByName(@PathVariable String nameRestaurant) {
        return super.getFilteredByName(nameRestaurant);
    }

    @PostMapping(value = "/restaurant", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant create(@RequestBody Restaurant restaurant) {
        return super.create(restaurant);
    }

    @PutMapping(value = "/restaurant", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant) {
        super.update(restaurant);
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId) {
        super.delete(restaurantId);
    }

}
