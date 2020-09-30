package ru.whereToEat.service;

import org.springframework.stereotype.Service;
import ru.whereToEat.model.Meal;
import ru.whereToEat.repository.MealRepository;

import java.util.List;

@Service
public class MealService {
    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public List<Meal> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    public List<Meal> getAll() {
        return repository.getAll();
    }

    public Meal get(int mealId) {
        return repository.get(mealId);
    }

    public void delete(int mealId) {
        repository.delete(mealId);
    }

    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    public Meal update(Meal meal) {
        return repository.save(meal);
    }

    public Meal create(Meal meal) {
        return repository.save(meal);
    }
}
