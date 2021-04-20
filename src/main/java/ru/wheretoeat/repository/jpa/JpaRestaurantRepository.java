package ru.wheretoeat.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.wheretoeat.model.Restaurant;
import ru.wheretoeat.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRestaurantRepository implements RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    @Override
    @Transactional
    public boolean delete(int restaurantId) {
        Restaurant restaurant = em.getReference(Restaurant.class, restaurantId);
        em.remove(restaurant);
        return !em.contains(restaurant);
    }

    @Override
    public Restaurant get(int restaurantId) {
        return em.find(Restaurant.class, restaurantId);
    }

    @Override
    public List<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.ALL_SORTED, Restaurant.class)
                .getResultList();
    }
}
