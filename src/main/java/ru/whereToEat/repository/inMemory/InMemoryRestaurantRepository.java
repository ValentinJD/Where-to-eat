package ru.whereToEat.repository.inMemory;

import org.springframework.stereotype.Repository;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.repository.RestaurantRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryRestaurantRepository implements RestaurantRepository {
    static Map<Integer, Restaurant> storage = new HashMap<>();

    @Override
    public Restaurant save(Restaurant restaurant) {
        return storage.put(restaurant.getRestaraunt_Id(), restaurant);
    }

    @Override
    public boolean delete(int id) {
        return storage.remove(id) != null;
    }

    @Override
    public Restaurant get(int id) {
        return storage.get(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return (List<Restaurant>) storage.values();
    }
}
