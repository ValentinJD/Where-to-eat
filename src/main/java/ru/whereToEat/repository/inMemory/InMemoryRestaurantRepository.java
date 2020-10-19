package ru.whereToEat.repository.inMemory;

import org.springframework.stereotype.Repository;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.repository.RestaurantRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class InMemoryRestaurantRepository implements RestaurantRepository {
    static Map<Integer, Restaurant> storage = new HashMap<>();

    @Override
    public Restaurant save(Restaurant restaurant) {
        return storage.put(restaurant.getId(), restaurant);
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
        return storage.values()
                .stream()
                .sorted(Comparator.comparing(Restaurant::getId))
                .collect(Collectors.toList());
    }
}
