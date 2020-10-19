package ru.whereToEat.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.whereToEat.exceptions.NotEnoughRightsException;
import ru.whereToEat.model.Meal;
import ru.whereToEat.service.MealService;
import ru.whereToEat.web.SecurityUtil;

import java.util.List;

@Controller
public class MealRestController {
    private final MealService mealService;

    public MealRestController(MealService mealService) {
        this.mealService = mealService;
    }

    public Meal get(int mealId) {
        return mealService.get(mealId);
    }

    public void delete(int mealId) {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            mealService.delete(mealId);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }

    }

    public List<Meal> getAll(int restaurantId) {
        return mealService.getAll(restaurantId);
    }

    public Meal create(Meal meal) {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            return mealService.create(meal);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }
    }

    public void update(Meal meal) {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            mealService.update(meal);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }
    }
}
