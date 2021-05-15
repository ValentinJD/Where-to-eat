package ru.wheretoeat.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.wheretoeat.View;
import ru.wheretoeat.model.Meal;
import ru.wheretoeat.model.Restaurant;
import ru.wheretoeat.to.MealTo;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@ApiIgnore
@RestController
@RequestMapping("/profile/meals")
public class MealUIController extends AbstractMealController {

    @Override
    @GetMapping(value = "/one/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Meal> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealTo> getAllTo(@PathVariable() Integer restaurantId) {
        return super.getAllTo(restaurantId);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<String> createOrUpdate(@RequestParam("restaurantId") Integer restaurantId, @Valid Meal meal) {

        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        meal.setRestaurant(restaurant);
        if (meal.isNew()) {
            super.create(meal);
        } else {
            super.update(meal);
        }

        return ResponseEntity.ok().build();
    }
}
