package ru.whereToEat.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.whereToEat.TestMatcher;
import ru.whereToEat.model.Meal;
import ru.whereToEat.repository.JpaUtil;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertThrows;
import static ru.whereToEat.MealTestData.*;
import static ru.whereToEat.MealTestData.getUpdated;

abstract public class AbstractMealServiceTest extends AbstractServiceTest {

    @Autowired
    protected MealService service;

    @Test
    public void get() {
        Meal expected = service.get(MEDALYONY_IZ_GOVYADINY_ID);
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(MEAL_FOR_GET, expected);
    }

    @Test
    public void delete() {
        service.delete(MEDALYONY_IZ_GOVYADINY_ID);
        assertThrows(EntityNotFoundException.class,
                () -> service.delete(MEDALYONY_IZ_GOVYADINY_ID));
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
        int id = updated.id();
        actual.setId(id);
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(actual, updated);
        testMatcher.assertMatch(actual, service.get(id));
    }
}
