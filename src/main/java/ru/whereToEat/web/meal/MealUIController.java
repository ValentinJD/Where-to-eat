package ru.whereToEat.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping("/profile/meals")
public class MealUIController extends AbstractMealController{

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Meal> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Meal> getAll(@PathVariable int id) {
        return super.getAll(id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(@RequestParam(required = false) Integer mealId,
                               @RequestParam String description,
                               @RequestParam Float price,
                               @RequestParam int restaurantId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        Meal meal = new Meal(mealId, description, price, restaurant);
        if (meal.isNew()) {
            super.create(meal);
        }
    }
}
