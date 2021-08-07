package ru.wheretoeat.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.wheretoeat.RestaurantTestData;
import ru.wheretoeat.model.Restaurant;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.wheretoeat.RestaurantTestData.RESTAURANT_MATCHER;

abstract public class AbstractRestaurantServiceTest extends AbstractServiceTest {
    @Autowired
    protected RestaurantService service;

    @Test
    public void get() {
        Restaurant expected = service.get(RestaurantTestData.PERCHINI_ID);
        RESTAURANT_MATCHER.assertMatch(RestaurantTestData.PERCHINI, expected);
    }

    @Test
    public void delete() {
        service.delete(RestaurantTestData.PERCHINI_ID);
        assertThrows(EntityNotFoundException.class, () -> service.delete(RestaurantTestData.PERCHINI_ID));
    }


    @Test
    public void update() {
        Restaurant actual = RestaurantTestData.getUpdated();
        Restaurant updated = service.update(RestaurantTestData.getUpdated());
        Integer id = updated.getId();
        actual.setId(id);
        RESTAURANT_MATCHER.assertMatch(actual, updated);
        RESTAURANT_MATCHER.assertMatch(actual, service.get(id));
    }

    @Test
    public void getAll() {
        List<Restaurant> expected = service.getAll();
//        RESTAURANT_MATCHER.assertMatch(RestaurantTestData.RESTAURANTS, expected);
    }

    @Test
    public void create() {
        Restaurant actual = RestaurantTestData.getNew();
        Restaurant newRestaurant = service.create(RestaurantTestData.getNew());
        int id = newRestaurant.id();
        actual.setId(id);
        RESTAURANT_MATCHER.assertMatch(actual, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(actual, service.get(id));
    }
}
