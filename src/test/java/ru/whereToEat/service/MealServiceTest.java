package ru.whereToEat.service;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.whereToEat.MealTestData;
import ru.whereToEat.TestMatcher;
import ru.whereToEat.model.Meal;

import java.util.List;

import static org.junit.Assert.*;
import static ru.whereToEat.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal expected = service.get(MEDALYONY_IZ_GOVYADINY_ID);
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(MEAL_FOR_GET,expected);
    }

    @Test
    public void delete() {
        service.delete(MEDALYONY_IZ_GOVYADINY_ID);
        assertNull(service.get(MEDALYONY_IZ_GOVYADINY_ID));
    }

    @Test
    public void save() {
        Meal actual = service.save(getNew());
        Meal expected = service.get(actual.getId());
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(actual,expected);
    }

    @Test
    public void update() {
        Meal actual = getUpdated();
        Meal updated = service.update(getUpdated());
        Integer id = updated.getId();
        actual.setId(id);
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(actual, updated);
        testMatcher.assertMatch(actual, service.get(id));

    }

    @Test
    public void getAll() {
        List<Meal> expected = service.getAll();
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(MEALS, expected);

    }

    @Test
    public void getAllByRestaurant() {
        List<Meal> expected = service.getAll(PERCHINI_ID);
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(MEALS_PERCHINI, expected);
    }

    @Test
    public void create() {
        Meal actual = getUpdated();
        Meal updated = service.create(getUpdated());
        Integer id = updated.getId();
        actual.setId(id);
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(actual, updated);
        testMatcher.assertMatch(actual, service.get(id));
    }
}