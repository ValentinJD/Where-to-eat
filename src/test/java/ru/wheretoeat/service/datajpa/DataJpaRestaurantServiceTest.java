package ru.wheretoeat.service.datajpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.wheretoeat.MealTestData;
import ru.wheretoeat.RestaurantTestData;
import ru.wheretoeat.exceptions.NotFoundException;
import ru.wheretoeat.model.Meal;
import ru.wheretoeat.model.Restaurant;
import ru.wheretoeat.service.AbstractRestaurantServiceTest;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.wheretoeat.Profiles.DATAJPA;
import static ru.wheretoeat.RestaurantTestData.PERCHINI;
import static ru.wheretoeat.RestaurantTestData.RESTAURANT_MATCHER;

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
