package ru.whereToEat.repository.springJdbc;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.whereToEat.model.Meal;
import ru.whereToEat.repository.MealRepository;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional(readOnly = true)
public class SpringJdbcMealRepository implements MealRepository {

    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;

    public SpringJdbcMealRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("meals")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    @Transactional
    public Meal save(Meal meal) {
        Objects.nonNull(meal.getRestaurant());
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("description", meal.getDescription())
                .addValue("price", meal.getPrice())
                .addValue("restaurant_id", meal.getRestaurant().getId());


        if (meal.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(map);
            meal.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE meals SET description=:description, price=:price, restaurant_id=:restaurant_id" +
                        " WHERE id=:id", map) == 0) {
            return null;
        }
        return meal;
    }

    @Override
    @Transactional
    public boolean delete(int mealId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id=?", mealId) != 0;
    }

    @Override
    public Meal get(int mealId) {
        List<Meal> meals = jdbcTemplate.query("SELECT * FROM meals WHERE id=?", ROW_MAPPER, mealId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        return jdbcTemplate.query("SELECT * FROM meals where restaurant_id = ? ORDER BY id desc ", ROW_MAPPER, restaurantId);
    }

    @Override
    public List<Meal> getAll() {
        return jdbcTemplate.query("SELECT * FROM meals ORDER BY id desc ", ROW_MAPPER);
    }
}
