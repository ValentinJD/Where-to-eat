package ru.wheretoeat.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.wheretoeat.View;
import ru.wheretoeat.model.Meal;
import ru.wheretoeat.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = MealAdminRestController.REST_URL_MEAL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealAdminRestController extends AbstractMealController {
    public static final String REST_URL_MEAL = "/rest/meal/";


    @GetMapping(value = "{mealId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@PathVariable int mealId) {
        return super.get(mealId);
    }


    @GetMapping(value = "byRestaurantId/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Meal> getByRestaurantId(@PathVariable int restaurantId) {
        return super.getAll(restaurantId);
    }

    @PostMapping(value = "{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Meal create(@Validated(View.Web.class) @RequestBody Meal meal, @PathVariable int restaurantId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        meal.setRestaurant(restaurant);
        return super.create(meal);
    }

    @PutMapping(value = "{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Meal meal, @PathVariable int restaurantId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        meal.setRestaurant(restaurant);
        super.update(meal);
    }

    @Override
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
