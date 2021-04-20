package ru.wheretoeat.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.wheretoeat.RestaurantTestData;
import ru.wheretoeat.exceptions.NotFoundException;
import ru.wheretoeat.model.Restaurant;
import ru.wheretoeat.service.RestaurantService;
import ru.wheretoeat.web.AbstractControllerTest;
import ru.wheretoeat.web.json.JsonUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.wheretoeat.RestaurantTestData.*;
import static ru.wheretoeat.RestaurantTestData.BAR_AND_GRIL;
import static ru.wheretoeat.RestaurantTestData.PERCHINI;
import static ru.wheretoeat.RestaurantTestData.PERCHINI_ID;
import static ru.wheretoeat.RestaurantTestData.TRI_OLENYA;
import static ru.wheretoeat.TestUtil.readFromJson;
import static ru.wheretoeat.TestUtil.userHttpBasic;
import static ru.wheretoeat.UserTestData.ADMIN;

public class RestaurantRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL_RESTAURANT = RestaurantRestController.REST_URL_RESTAURANT + "/restaurant/";
    private static final String REST_URL_RESTAURANTS = RestaurantRestController.REST_URL_RESTAURANT + "/restaurants";

    @Autowired
    private RestaurantService service;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_RESTAURANT + PERCHINI_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(PERCHINI));
    }


    @Test
    void getAll() throws Exception {
        List<Restaurant> list = List.of(PERCHINI, BAR_AND_GRIL, TRI_OLENYA);
        perform(MockMvcRequestBuilders.get(REST_URL_RESTAURANTS)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(RESTAURANT_MATCHER.contentJson(list));
    }

    @Test
    void getAllFilteredByName() throws Exception {
        JsonUtil.writeValue(PERCHINI);
        List<Restaurant> list = List.of(PERCHINI);
        perform(MockMvcRequestBuilders.get(REST_URL_RESTAURANTS + "/filter/Перчини")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(RESTAURANT_MATCHER.contentJson(list));
    }

    @Test
    @Transactional
    void createWithLocation() throws Exception {
        Restaurant restaurant = RestaurantTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL_RESTAURANT)
                .with(userHttpBasic(ADMIN))
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
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        RESTAURANT_MATCHER.assertMatch(service.get(PERCHINI_ID), updated);
    }

    @Test
    @Transactional()
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_RESTAURANT + PERCHINI_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> service.get(PERCHINI_ID));
    }


}
