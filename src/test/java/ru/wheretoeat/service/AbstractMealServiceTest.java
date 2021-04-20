package ru.wheretoeat.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.wheretoeat.model.Meal;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.wheretoeat.MealTestData.*;

abstract public class AbstractMealServiceTest extends AbstractServiceTest {

    @Autowired
    protected MealService service;

    @Test
    void get() {
        Meal expected = service.get(MEDALYONY_IZ_GOVYADINY_ID);
        RESTAURANT_MEAL_MATCHER.assertMatch(MEAL_FOR_GET, expected);
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
        RESTAURANT_MEAL_MATCHER.assertMatch(actual, updated);
        RESTAURANT_MEAL_MATCHER.assertMatch(actual, service.get(id));
    }

    @Test
    void getAll() {
        List<Meal> expected = service.getAll();
        RESTAURANT_MEAL_MATCHER.assertMatch(MEALS, expected);
    }

    @Test
    void getAllByRestaurant() {
        List<Meal> expected = service.getAll(PERCHINI_ID);
        RESTAURANT_MEAL_MATCHER.assertMatch(MEALS_PERCHINI, expected);
    }

    @Test
    void create() {
        Meal actual = getUpdated();
        Meal updated = service.create(getUpdated());
        int id = updated.id();
        actual.setId(id);
        RESTAURANT_MEAL_MATCHER.assertMatch(actual, updated);
        RESTAURANT_MEAL_MATCHER.assertMatch(actual, service.get(id));
    }
}
