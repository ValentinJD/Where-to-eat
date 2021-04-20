package ru.wheretoeat.web.json;

import org.junit.jupiter.api.Test;
import ru.wheretoeat.model.Meal;

import java.util.List;

import static ru.wheretoeat.MealTestData.*;

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