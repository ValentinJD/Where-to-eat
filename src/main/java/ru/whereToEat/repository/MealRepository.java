package ru.whereToEat.repository;

import ru.whereToEat.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {
    // null if updated meal do not belong to userId
    Meal save(Meal meal);

    // false if meal do not belong to userId
    boolean delete(int mealId);

    // null if meal do not belong to userId
    Meal get(int mealId);

    // ORDERED dateTime desc
    List<Meal> getAll(int restaurantId);

    List<Meal> getAll();

    // ORDERED dateTime desc
    List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime);

    default Meal getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
