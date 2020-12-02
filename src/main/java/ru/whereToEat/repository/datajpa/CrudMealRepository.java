package ru.whereToEat.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.whereToEat.model.Meal;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
}
