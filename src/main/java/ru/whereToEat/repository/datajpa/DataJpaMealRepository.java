package ru.whereToEat.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.whereToEat.model.Meal;
import ru.whereToEat.repository.MealRepository;

import java.util.Comparator;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {
    private static final Sort SORT_ID = Sort.by(Sort.Direction.DESC, "id");


    private final CrudMealRepository crudRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Meal save(Meal meal) {
        if (!meal.isNew() && get(meal.getId()) == null) {
            return null;
        }
        return crudRepository.save(meal);
    }

    @Override
    public boolean delete(int mealId) {
        return crudRepository.delete(mealId) != 0;
    }

    @Override
    public Meal get(int mealId) {
        return crudRepository.findById(mealId).orElse(null);
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        List<Meal> list = crudRepository.findAllByRestaurantId(restaurantId);
        list.sort(
                (o1, o2) -> o2.getId().compareTo(
                        o1.getId()
                )
        );

        return list;
    }

    @Override
    public List<Meal> getAll() {
        return crudRepository.findAll(SORT_ID);
    }
}