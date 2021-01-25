package ru.whereToEat.service.datajpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.MealTestData;
import ru.whereToEat.RestaurantTestData;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.service.AbstractRestaurantServiceTest;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.whereToEat.Profiles.DATAJPA;
import static ru.whereToEat.RestaurantTestData.PERCHINI;
import static ru.whereToEat.RestaurantTestData.RESTAURANT_MATCHER;

@ActiveProfiles(DATAJPA)
class DataJpaRestaurantServiceTest extends AbstractRestaurantServiceTest {

    @Override
    public void delete() {
        service.delete(RestaurantTestData.PERCHINI_ID);
        assertThrows(NotFoundException.class, () -> service.delete(RestaurantTestData.PERCHINI_ID));
    }

    @Test
    public void getWithMeals() throws Exception {
        Restaurant restaurant = service.getWithMeals(RestaurantTestData.PERCHINI_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, PERCHINI);
        List<Meal> menu = restaurant.getMenu();
        menu.sort(Comparator.comparingInt(Meal::getId).reversed());
        MealTestData.RESTAURANT_MEAL_MATCHER.assertMatch(menu, MealTestData.MEALS_PERCHINI);
    }


    @Test
    public void getWithMealsNotFound() throws Exception {
        Assertions.assertThrows(NotFoundException.class,
                () -> service.getWithMeals(1));
    }

}
