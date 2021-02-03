package ru.whereToEat.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.whereToEat.MealTestData;
import ru.whereToEat.RestaurantTestData;
import ru.whereToEat.VoteTestData;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.model.Vote;
import ru.whereToEat.service.RestaurantService;
import ru.whereToEat.service.VoteService;
import ru.whereToEat.web.AbstractControllerTest;
import ru.whereToEat.web.json.JsonUtil;

import java.time.Clock;
import java.time.Duration;
import java.time.ZoneId;
import java.util.List;

import static java.time.Instant.ofEpochMilli;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.whereToEat.MealTestData.*;
import static ru.whereToEat.MealTestData.MEDALYONY_IZ_GOVYADINY_ID;
import static ru.whereToEat.RestaurantTestData.*;
import static ru.whereToEat.RestaurantTestData.BAR_AND_GRIL;
import static ru.whereToEat.RestaurantTestData.PERCHINI;
import static ru.whereToEat.RestaurantTestData.PERCHINI_ID;
import static ru.whereToEat.RestaurantTestData.TRI_OLENYA;
import static ru.whereToEat.TestUtil.readFromJson;

public class RestaurantRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL_RESTAURANT = RestaurantRestController.REST_URL_RESTAURANT + "/restaurant/";
    private static final String REST_URL_RESTAURANTS = RestaurantRestController.REST_URL_RESTAURANT + "/restaurants";

    @Autowired
    private RestaurantService service;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_RESTAURANT + PERCHINI_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(PERCHINI));
    }


    @Test
    void getAll() throws Exception {
        List<Restaurant> list = List.of(PERCHINI, BAR_AND_GRIL, TRI_OLENYA);
        perform(MockMvcRequestBuilders.get(REST_URL_RESTAURANTS))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(RESTAURANT_MATCHER.contentJson(list));
    }

    @Test
    void getAllFilteredByName() throws Exception {
        JsonUtil.writeValue(PERCHINI);
        List<Restaurant> list = List.of(PERCHINI);
        perform(MockMvcRequestBuilders.get(REST_URL_RESTAURANTS + "/filter/Перчини"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(RESTAURANT_MATCHER.contentJson(list));
    }

    @Test
    @Transactional
    void createWithLocation() throws Exception {
        Restaurant restaurant = RestaurantTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL_RESTAURANT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(restaurant)))
                .andExpect(status().isCreated());

        Restaurant created = readFromJson(action, Restaurant.class);
        int newId = created.id();
        restaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, restaurant);
        RESTAURANT_MATCHER.assertMatch(service.get(newId), restaurant);
    }


    @Test
    @Transactional
    void update() throws Exception {
        Restaurant updated = RestaurantTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL_RESTAURANT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        RESTAURANT_MATCHER.assertMatch(service.get(PERCHINI_ID), updated);
    }

    @Test
    @Transactional()
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_RESTAURANT + PERCHINI_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> service.get(PERCHINI_ID));
    }


}
