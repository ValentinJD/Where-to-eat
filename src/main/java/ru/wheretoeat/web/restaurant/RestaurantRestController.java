package ru.wheretoeat.web.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.wheretoeat.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL_RESTAURANT, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController extends AbstractRestaurantController {
    public static final String REST_URL_RESTAURANT = "/rest/restaurant";

    @GetMapping(value = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable int restaurantId) {
        return super.get(restaurantId);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/filter/{nameRestaurant}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getFilteredByName(@PathVariable String nameRestaurant) {
        return super.getFilteredByName(nameRestaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant create(@RequestBody Restaurant restaurant) {
        return super.create(restaurant);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant) {
        super.update(restaurant);
    }

    @DeleteMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId) {
        super.delete(restaurantId);
    }

}
