package ru.whereToEat.repository.springJdbc;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.repository.RestaurantRepository;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional(readOnly = true)
public class SpringJdbcRestaurantRepository implements RestaurantRepository {
    private static final BeanPropertyRowMapper<Restaurant> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Restaurant.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;

    public SpringJdbcRestaurantRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("restaurants")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Objects.nonNull(restaurant);
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", restaurant.getId())
                .addValue("name", restaurant.getName())
                .addValue("vote_count", restaurant.getVote_count());


        if (restaurant.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(map);
            restaurant.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE restaurants SET name=:name, vote_count=:vote_count" +
                        " WHERE id=:id", map) == 0) {
            return null;
        }
        return restaurant;
    }

    @Override
    @Transactional
    public boolean delete(int restaurantId) {
        return jdbcTemplate.update("DELETE FROM restaurants WHERE id=?", restaurantId) != 0;
    }

    @Override
    public Restaurant get(int restaurantId) {
        List<Restaurant> restaurants = jdbcTemplate.query("SELECT * FROM restaurants WHERE id=?", ROW_MAPPER, restaurantId);
        return DataAccessUtils.singleResult(restaurants);
    }

    @Override
    public List<Restaurant> getAll() {
        return jdbcTemplate.query("SELECT * FROM restaurants Order By id", ROW_MAPPER);
    }
}
