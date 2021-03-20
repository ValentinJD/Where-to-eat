package ru.whereToEat.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile/meals")
public class MealUIController extends AbstractMealController {

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
    public ResponseEntity<String> createOrUpdate(@Valid Meal meal, BindingResult result, @RequestParam Integer restaurantId) {
        if (result.hasErrors()) {
            String errorFieldsMsg = result.getFieldErrors().stream()
                    .map(fe -> String.format("[%s] %s", fe.getField(), fe.getDefaultMessage()))
                    .collect(Collectors.joining("<br>"));
            return ResponseEntity.unprocessableEntity().body(errorFieldsMsg);
        }


        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        meal.setRestaurant(restaurant);
        if (meal.isNew()) {
            super.create(meal);
        }

        return ResponseEntity.ok().build();
    }
}
