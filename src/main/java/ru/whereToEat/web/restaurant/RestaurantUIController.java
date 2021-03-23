package ru.whereToEat.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.whereToEat.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping("/profile/restaurants")
public class RestaurantUIController extends AbstractRestaurantController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> getAllId() {
        return super.getAllId();
    }
}
