package ru.wheretoeat.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.wheretoeat.model.Meal;
import ru.wheretoeat.model.Restaurant;
import ru.wheretoeat.repository.MealRepository;
import ru.wheretoeat.util.dbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class JDBCMealRepository implements MealRepository {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private Connection connection;

    @Override
    public Meal save(Meal meal) {
        connection = dbUtil.getConnection();

        PreparedStatement preparedStatement = null;

        if (meal.isNew()) {

            try {
                preparedStatement = connection
                        .prepareStatement("insert into meals(description, price, restaurant_id) values (?,?,?)");
                // Parameters start with 1
                preparedStatement.setString(1, meal.getDescription());
                preparedStatement.setFloat(2, meal.getPrice());
                preparedStatement.setInt(3, meal.getRestaurant().getId());
                preparedStatement.executeUpdate();

                log.info("save {}", meal);

                return meal;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {

            try {
                preparedStatement = connection
                        .prepareStatement("update meals set description=?, price=?" +
                                "where id = ? and restaurant_id=? ");
                // Parameters start with 1
                preparedStatement.setString(1, meal.getDescription());
                preparedStatement.setFloat(2, meal.getPrice());
                preparedStatement.setInt(3, meal.getId());
                preparedStatement.setInt(4, meal.getRestaurant().getId());

                int count = preparedStatement.executeUpdate();

                if (count == 0) {
                    return null;
                } else {

                    log.info("update {}", meal);
                    return meal;
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }

        return null;
    }

    @Override
    public boolean delete(int mealId) {
        connection = dbUtil.getConnection();

        Integer count = null;

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("delete from meals where id=?");
            preparedStatement.setInt(1, mealId);
            count = preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        log.info("delete mealId {}", mealId);

        return count == 1;
    }

    @Override
    public Meal get(int mealId) {
        connection = dbUtil.getConnection();

        Meal meal = new Meal();

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from meals where id=?");
            preparedStatement.setInt(1, mealId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                meal.setId(rs.getInt("id"));
                meal.setDescription(rs.getString("description"));
                meal.setPrice(rs.getFloat("price"));
                Restaurant restaurant = new Restaurant();
                restaurant.setId(rs.getInt("restaurant_id"));
                meal.setRestaurant(restaurant);

            }
            meal.setId(mealId);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        log.info("get {}", meal);

        return meal;
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        connection = dbUtil.getConnection();

        List<Meal> meals = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from meals" +
                            " where restaurant_id = ?");
            preparedStatement.setInt(1, restaurantId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Meal meal = new Meal();
                meal.setId(rs.getInt("id"));
                meal.setDescription(rs.getString("description"));
                meal.setPrice(rs.getFloat("price"));
                Restaurant restaurant = new Restaurant();
                restaurant.setId(restaurantId);
                meal.setRestaurant(restaurant);
                meals.add(meal);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        log.info("getAll");

        meals.sort(Comparator.comparing(Meal::getId).reversed());

        return meals;
    }

    @Override
    public List<Meal> getAll() {
        connection = dbUtil.getConnection();

        List<Meal> meals = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from meals");
            while (rs.next()) {
                Meal meal = new Meal();
                meal.setId(rs.getInt("id"));
                meal.setDescription(rs.getString("description"));
                meal.setPrice(rs.getFloat("price"));
                meals.add(meal);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        log.info("getAll");
        meals.sort(Comparator.comparing(Meal::getId).reversed());
        return meals;
    }


}
