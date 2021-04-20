package ru.wheretoeat.util;

import ru.wheretoeat.model.Meal;
import ru.wheretoeat.to.MealTo;

import java.util.List;
import java.util.stream.Collectors;

public class MealsUtil {
    public static MealTo createTo(Meal meal, Integer restaurantId) {
        return new MealTo(meal.id(), meal.getDescription(), meal.getPrice(), restaurantId);
    }

    public static List<MealTo> createListTo(List<Meal> meals, Integer restaurantId) {
        return meals.stream()
                .map(meal -> createTo(meal, restaurantId))
                .collect(Collectors.toList());
    }
}
