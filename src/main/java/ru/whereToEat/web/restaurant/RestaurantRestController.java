package ru.whereToEat.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.whereToEat.exceptions.NotEnoughRightsException;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.service.RestaurantService;
import ru.whereToEat.web.SecurityUtil;

import java.util.List;

@Controller
public class RestaurantRestController {
    @Autowired
    RestaurantService restaurantService;

    public Restaurant get(int restaurantId) {
        return restaurantService.get(restaurantId);
    }

    public void delete(int restaurantId) {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            restaurantService.delete(restaurantId);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }

    }

    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    public Restaurant create(Restaurant restaurant) {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            return restaurantService.create(restaurant);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }
    }

    public void update(Restaurant restaurant) {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            restaurantService.update(restaurant);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }
    }
}
