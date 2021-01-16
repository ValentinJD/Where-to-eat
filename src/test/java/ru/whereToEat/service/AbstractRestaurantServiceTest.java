package ru.whereToEat.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.whereToEat.RestaurantTestData;
import ru.whereToEat.TestMatcher;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.repository.JpaUtil;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertThrows;

abstract public class AbstractRestaurantServiceTest extends AbstractServiceTest {
    @Autowired
    protected RestaurantService service;

    @Test
    public void get() {
        Restaurant expected = service.get(RestaurantTestData.PERCHINI_ID);
        TestMatcher<Restaurant> testMatcher = TestMatcher.usingFieldsComparator("menu");
        testMatcher.assertMatch(RestaurantTestData.PERCHINI, expected);
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
        TestMatcher<Restaurant> testMatcher = TestMatcher.usingFieldsComparator("menu");
        testMatcher.assertMatch(actual, updated);
        testMatcher.assertMatch(actual, service.get(id));
    }

    @Test
    public void getAll() {
        List<Restaurant> expected = service.getAll();
        TestMatcher<Restaurant> testMatcher = TestMatcher.usingFieldsComparator("menu");
        testMatcher.assertMatch(RestaurantTestData.RESTAURANTS, expected);
    }

    @Test
    public void create() {
        Restaurant actual = RestaurantTestData.getNew();
        Restaurant newRestaurant = service.create(RestaurantTestData.getNew());
        int id = newRestaurant.id();
        actual.setId(id);
        TestMatcher<Restaurant> testMatcher = TestMatcher.usingFieldsComparator("menu");
        testMatcher.assertMatch(actual, newRestaurant);
        testMatcher.assertMatch(actual, service.get(id));
    }
}
