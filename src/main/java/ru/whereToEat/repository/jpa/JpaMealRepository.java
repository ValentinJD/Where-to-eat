package ru.whereToEat.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            return em.merge(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int mealId) {
        Meal meal = em.getReference(Meal.class, mealId);
        em.remove(meal);
        return !em.contains(meal);
    }

    @Override
    public Meal get(int mealId) {
        return em.find(Meal.class, mealId);
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        return em.createNamedQuery(Meal.ALL_SORTED_BY_RESTAURANT_ID, Meal.class)
                .setParameter(1, restaurantId)
                .getResultList();
    }

    @Override
    public List<Meal> getAll() {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .getResultList();
    }


}