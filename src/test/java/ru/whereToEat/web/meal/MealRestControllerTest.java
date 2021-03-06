package ru.whereToEat.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.whereToEat.MealTestData;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.model.Meal;
import ru.whereToEat.service.MealService;
import ru.whereToEat.web.AbstractControllerTest;
import ru.whereToEat.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.whereToEat.MealTestData.*;
import static ru.whereToEat.TestUtil.readFromJson;

public class MealRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL_MEAL = MealRestController.REST_URL_MEAL + "/meal/";
    private static final String REST_URL_MEALS = MealRestController.REST_URL_MEAL + "/meals/";

    @Autowired
    private MealService mealService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_MEAL + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MEAL_MATCHER.contentJson(MEAL1));
    }

    @Test
    @Transactional
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_MEAL + MEDALYONY_IZ_GOVYADINY_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> mealService.get(MEDALYONY_IZ_GOVYADINY_ID));
    }

    @Test
    @Transactional
    void update() throws Exception {
        Meal updated = MealTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL_MEAL + MEDALYONY_IZ_GOVYADINY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        RESTAURANT_MEAL_MATCHER.assertMatch(mealService.get(MEDALYONY_IZ_GOVYADINY_ID), updated);
    }

    @Test
    @Transactional
    void createWithLocation() throws Exception {
        Meal meal = MealTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL_MEAL + '/' + PERCHINI_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(meal)))
                .andExpect(status().isCreated());

        Meal created = readFromJson(action, Meal.class);
        int newId = created.id();
        meal.setId(newId);
        RESTAURANT_MEAL_MATCHER.assertMatch(created, meal);
        RESTAURANT_MEAL_MATCHER.assertMatch(mealService.get(newId), meal);
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_MEALS + PERCHINI_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MEAL_MATCHER.contentJson(MEAL3, MEAL2, MEAL1));
    }
}
