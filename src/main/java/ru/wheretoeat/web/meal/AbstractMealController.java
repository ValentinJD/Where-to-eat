package ru.wheretoeat.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.wheretoeat.model.Meal;
import ru.wheretoeat.service.MealService;
import ru.wheretoeat.service.RestaurantService;
import ru.wheretoeat.to.MealTo;

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
        mealService.delete(mealId);
        log.info("delete() {}", mealId);
    }

    public List<Meal> getAll(Integer restaurantId) {
        log.info("getAll() {}", restaurantId);
        return mealService.getAll(restaurantId);
    }

    public List<Meal> getAll() {
        log.info("getAll() ");
        return mealService.getAll();
    }

    public List<MealTo> getAllTo(Integer restaurantId) {
        log.info("getAll() ");
        return mealService.getAllTo(getAll(restaurantId), restaurantId);
    }

    public Meal create(Meal meal) {
        log.info("create() {}", meal);
        return mealService.create(meal);
    }

    public void update(Meal meal) {
        mealService.update(meal);
        log.info("update() {}", meal);
    }
}
