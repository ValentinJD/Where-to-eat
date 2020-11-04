package ru.whereToEat.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.whereToEat.model.Meal;
import ru.whereToEat.repository.MealRepository;

import java.util.List;

import static ru.whereToEat.util.ValidationUtil.checkNotFoundWithId;

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
        checkNotFoundWithId(repository.delete(mealId), mealId);
    }

    public Meal update(Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal);
    }

    public Meal create(Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal);
    }
}
