package ru.wheretoeat.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.wheretoeat.exceptions.NotEnoughRightsException;
import ru.wheretoeat.exceptions.NotVoteException;
import ru.wheretoeat.model.Restaurant;
import ru.wheretoeat.model.Vote;
import ru.wheretoeat.service.RestaurantService;
import ru.wheretoeat.service.VoteService;
import ru.wheretoeat.web.SecurityUtil;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private VoteService voteService;

    public Restaurant get(int restaurantId) {
        return restaurantService.get(restaurantId);
    }

    public List<Integer> getAllId() {
        log.info("getAll()");
        return restaurantService.getAllRestaurantId();
    }

    public void delete(int restaurantId) {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            restaurantService.delete(restaurantId);
            log.info("Удален ресторан() {}", restaurantId);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }

    }

    public List<Restaurant> getAll() {
        log.info("getAll()");
        return restaurantService.getAll();
    }

    public Restaurant create(Restaurant restaurant) {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            log.info("create() Restaurant {}", restaurant);
            return restaurantService.create(restaurant);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }
    }

    public void update(Restaurant restaurant) {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            restaurantService.update(restaurant);
            log.info("update() Restaurant {}", restaurant);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }
    }

    public List<Restaurant> getFilteredByName(String name) {
        log.info("getFilteredByName() {}", name);
        Predicate<Restaurant> filter = restaurant -> {
            if (!name.equals("")) {
                return restaurant.getName().toLowerCase().contains(name.toLowerCase());
            }
            return true;
        };
        return getAll().stream()
                .filter(filter)
                .sorted(Comparator.comparing(Restaurant::getId))
                .collect(Collectors.toList());

    }

    public void voter(Vote vote) throws NotVoteException {
        voteService.voter(vote);
    }
}
