package ru.whereToEat.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.whereToEat.model.Restaurant;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
