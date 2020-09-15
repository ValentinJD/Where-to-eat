package ru.whereToEat.service;

import ru.whereToEat.model.Meal;
import ru.whereToEat.repository.MealRepository;

import java.util.List;

public class MealService {
    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public List<Meal> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    public Meal get(int mealId, int userId) {
        return repository.get(mealId, userId);
    }

    public void delete(int mealId, int userId) {
        repository.delete(mealId, userId);
    }

    public Meal save(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    public Meal update(Meal meal, int userId) {
        return repository.save(meal, userId);
    }
}
