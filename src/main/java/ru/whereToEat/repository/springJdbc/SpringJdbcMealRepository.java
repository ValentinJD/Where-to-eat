package ru.whereToEat.repository.springJdbc;

import org.springframework.stereotype.Repository;
import ru.whereToEat.model.Meal;
import ru.whereToEat.repository.MealRepository;

import java.util.List;

@Repository
public class SpringJdbcMealRepository implements MealRepository {
    @Override
    public Meal save(Meal meal) {
        return null;
    }

    @Override
    public boolean delete(int mealId) {
        return false;
    }

    @Override
    public Meal get(int mealId) {
        return null;
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        return null;
    }

    @Override
    public List<Meal> getAll() {
        return null;
    }
}
