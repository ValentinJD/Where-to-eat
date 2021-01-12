package ru.whereToEat.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.whereToEat.exceptions.NotEnoughRightsException;
import ru.whereToEat.model.Meal;
import ru.whereToEat.service.MealService;
import ru.whereToEat.service.RestaurantService;
import ru.whereToEat.web.SecurityUtil;

import java.util.List;

public abstract class AbstractMealController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService mealService;

    @Autowired
    protected RestaurantService restaurantService;

    public Meal get(int mealId) {
        log.info("get() {}", mealId);
        return mealService.get(mealId);
    }

    public void delete(int mealId) {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            mealService.delete(mealId);
            log.info("delete() {}", mealId);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }
    }

    public List<Meal> getAll(int restaurantId) {
        log.info("getAll() {}", restaurantId);
        return mealService.getAll(restaurantId);
    }

    public List<Meal> getAll() {
        log.info("getAll() ");
        return mealService.getAll();
    }

    public Meal create(Meal meal) {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            log.info("create() {}", meal);
            return mealService.create(meal);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }
    }

    public void update(Meal meal) {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            mealService.update(meal);
            log.info("update() {}", meal);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }
    }


}
