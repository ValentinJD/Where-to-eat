package ru.whereToEat.repository.inMemory;

import ru.whereToEat.model.Meal;
import ru.whereToEat.model.User;
import ru.whereToEat.repository.MealRepository;
import ru.whereToEat.util.TimeUtil;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    static Map<Integer, Meal> storage = new HashMap<>();

    @Override
    public Meal save(Meal meal) {
        return storage.put(meal.getId(),meal);
    }

    @Override
    public boolean delete(int id) {
        return storage.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        return storage.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return storage.values().stream().filter(meal -> meal.getId() == userId).collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAll() {
        return (List<Meal>) storage.values();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return null;
    }
}
