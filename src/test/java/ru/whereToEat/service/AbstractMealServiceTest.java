package ru.whereToEat.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.whereToEat.TestMatcher;
import ru.whereToEat.model.Meal;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.whereToEat.MealTestData.*;

abstract public class AbstractMealServiceTest extends AbstractServiceTest {

    @Autowired
    protected MealService service;

    @Test
    void get() {
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
    void update() {
        Meal actual = getUpdated();
        Meal updated = service.update(getUpdated());
        Integer id = updated.getId();
        actual.setId(id);
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(actual, updated);
        testMatcher.assertMatch(actual, service.get(id));
    }

    @Test
    void getAll() {
        List<Meal> expected = service.getAll();
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(MEALS, expected);
    }

    @Test
    void getAllByRestaurant() {
        List<Meal> expected = service.getAll(PERCHINI_ID);
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(MEALS_PERCHINI, expected);
    }

    @Test
    void create() {
        Meal actual = getUpdated();
        Meal updated = service.create(getUpdated());
        int id = updated.id();
        actual.setId(id);
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(actual, updated);
        testMatcher.assertMatch(actual, service.get(id));
    }
}
