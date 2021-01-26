package ru.whereToEat.web.json;

import org.junit.jupiter.api.Test;
import ru.whereToEat.model.Meal;

import java.util.List;

import static ru.whereToEat.MealTestData.*;

class JsonUtilTest {

    @Test
    void readWriteValue() throws Exception {
        String json = JsonUtil.writeValue(MEAL1);
        System.out.println(json);
        Meal meal = JsonUtil.readValue(json, Meal.class);
        RESTAURANT_MEAL_MATCHER.assertMatch(meal, MEAL1);
    }

    @Test
    void readWriteValues() throws Exception {
        String json = JsonUtil.writeValue(MEALS);
        System.out.println(json);
        List<Meal> meals = JsonUtil.readValues(json, Meal.class);
        RESTAURANT_MEAL_MATCHER.assertMatch(meals, MEALS);
    }
}