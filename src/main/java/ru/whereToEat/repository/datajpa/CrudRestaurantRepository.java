package ru.whereToEat.repository.datajpa;

import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.whereToEat.model.Restaurant;

import javax.persistence.OrderBy;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);


    @EntityGraph(attributePaths = {"menu"})
    @Query("SELECT r FROM Restaurant r WHERE r.id = ?1 ")
    Restaurant getWithMeals(int restaurantId);
}
