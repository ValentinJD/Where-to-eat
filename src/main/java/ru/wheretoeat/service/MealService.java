package ru.wheretoeat.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.wheretoeat.model.Meal;
import ru.wheretoeat.repository.MealRepository;
import ru.wheretoeat.to.MealTo;
import ru.wheretoeat.util.MealsUtil;

import java.util.List;

import static ru.wheretoeat.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    @Cacheable("meals")
    public List<Meal> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Cacheable("meals")
    public List<Meal> getAll() {
        return repository.getAll();
    }

    public List<MealTo> getAllTo(List<Meal> list, Integer restaurantId) {
        return MealsUtil.createListTo(list, restaurantId);
    }

    @Cacheable("meals")
    public Meal get(int mealId) {
        Meal meal = repository.get(mealId);
        checkNotFoundWithId(meal, mealId);
        return meal;
    }

    @CacheEvict(value = {"meals", "restaurants"}, allEntries = true)
    public void delete(int mealId) {
        checkNotFoundWithId(repository.delete(mealId), mealId);
    }

    @CacheEvict(value = {"meals", "restaurants"}, allEntries = true)
    public Meal update(Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal);
    }

    @CacheEvict(value = {"meals", "restaurants"}, allEntries = true)
    public Meal create(Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal);
    }
}
