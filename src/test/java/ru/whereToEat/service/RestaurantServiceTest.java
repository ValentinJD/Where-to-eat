package ru.whereToEat.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.whereToEat.MealTestData;
import ru.whereToEat.RestaurantTestData;
import ru.whereToEat.TestMatcher;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;

import java.util.List;

import static org.junit.Assert.*;
import static ru.whereToEat.MealTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void get() {
        Restaurant expected = service.get(RestaurantTestData.PERCHINI_ID);
        TestMatcher<Restaurant> testMatcher = TestMatcher.usingFieldsComparator("vote_count");
        testMatcher.assertMatch(RestaurantTestData.PERCHINI,expected);
    }

    @Test
    public void delete() {
        service.delete(RestaurantTestData.PERCHINI_ID);
        assertNull(service.get(RestaurantTestData.PERCHINI_ID));
    }

    @Test
    public void save() {
        Restaurant actual = service.save(RestaurantTestData.getNew());
        Restaurant expected = service.get(actual.getId());
        TestMatcher<Restaurant> testMatcher = TestMatcher.usingFieldsComparator("vote_count");
        testMatcher.assertMatch(actual,expected);
    }

    @Test
    public void update() {
        Restaurant actual = RestaurantTestData.getUpdated();
        Restaurant updated = service.update(RestaurantTestData.getUpdated());
        Integer id = updated.getId();
        actual.setId(id);
        TestMatcher<Restaurant> testMatcher = TestMatcher.usingFieldsComparator("vote_count");
        testMatcher.assertMatch(actual, updated);
        testMatcher.assertMatch(actual, service.get(id));
    }

    @Test
    public void getAll() {
        List<Restaurant> expected = service.getAll();
        TestMatcher<Restaurant> testMatcher = TestMatcher.usingFieldsComparator("vote_count", "menu");
        testMatcher.assertMatch(RestaurantTestData.RESTAURANTS, expected);
    }

    @Test
    public void create() {
        Restaurant actual = RestaurantTestData.getNew();
        Restaurant newRestaurant = service.create(RestaurantTestData.getNew());
        Integer id = newRestaurant.getId();
        actual.setId(id);
        TestMatcher<Restaurant> testMatcher = TestMatcher.usingFieldsComparator("vote_count");
        testMatcher.assertMatch(actual, newRestaurant);
        testMatcher.assertMatch(actual, service.get(id));
    }
}