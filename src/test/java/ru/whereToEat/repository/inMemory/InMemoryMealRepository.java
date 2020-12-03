package ru.whereToEat.repository.inMemory;

import org.springframework.stereotype.Repository;
import ru.whereToEat.model.Meal;
import ru.whereToEat.repository.MealRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
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
        return storage.values().stream()
                .filter(meal -> meal.getId() == userId)
                .sorted(Comparator.comparing(Meal::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAll() {
        return storage.values().stream()
                .sorted(Comparator.comparing(Meal::getId))
                .collect(Collectors.toList());
    }

}
