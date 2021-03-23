package ru.whereToEat.util;

import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.to.MealTo;

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
