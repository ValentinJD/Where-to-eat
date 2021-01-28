package ru.whereToEat.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.model.User;
import ru.whereToEat.web.user.AdminRestController;

import java.util.List;

import static ru.whereToEat.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = MealRestController.REST_URL_MEAL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController extends AbstractMealController {
    public static final String REST_URL_MEAL = "/rest";


    @GetMapping(value = "/meal/{mealId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@PathVariable int mealId) {
        return super.get(mealId);
    }


    @GetMapping(value = "/meals/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Meal> getByRestaurantId(@PathVariable int restaurantId) {
        return super.getAll(restaurantId);
    }

    @PostMapping(value = "/meal/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Meal create(@RequestBody Meal meal, @PathVariable int restaurantId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        meal.setRestaurant(restaurant);
        return super.create(meal);
    }

    @PutMapping(value = "/meal/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int restaurantId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        meal.setRestaurant(restaurant);
        super.update(meal);
    }


}
