package ru.whereToEat.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.whereToEat.model.Meal;
import ru.whereToEat.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JpaMealRepository implements MealRepository {

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

    /*@Override
    public Meal save(Meal meal, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return null;
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return null;
    }*/


}