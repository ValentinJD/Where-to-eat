package ru.whereToEat.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.repository.RestaurantRepository;
import ru.whereToEat.util.dbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCRestaurantRepository implements RestaurantRepository {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private Connection connection;

    @Override
    public Restaurant save(Restaurant restaurant) {
        connection = dbUtil.getConnection();

        PreparedStatement preparedStatement = null;

        if (restaurant.isNew()) {

            try {
                preparedStatement = connection
                        .prepareStatement("insert into restaurants(name) values (?)");
                // Parameters start with 1
                preparedStatement.setString(1, restaurant.getName());
                preparedStatement.executeUpdate();

                log.info("save {}", restaurant);

                return restaurant;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }



        } else {

            try {
                preparedStatement = connection
                        .prepareStatement("update restaurants set name=?" +
                                "where id=?");
                // Parameters start with 1
                preparedStatement.setString(1, restaurant.getName());
                preparedStatement.setInt(2, restaurant.getRestaraunt_Id());

                int count = preparedStatement.executeUpdate();

                if (count == 0) {
                    return null;
                } else {

                    log.info("update {}", restaurant);
                    return restaurant;
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }

        return null;
    }

    private void setMenu(Restaurant restaurant) {

    }

    private List<Meal> getMenu(Restaurant restaurant) {
        return null;
    }

    private Integer getId(Restaurant restaurant) throws NotFoundException {
        return null; // id
    }

    @Override
    public boolean delete(int id) {

        connection = dbUtil.getConnection();

        Integer count = null;

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("delete from restaurants where id=?");
            preparedStatement.setInt(1, id);
            count = preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        log.info("delete {}", id);

        return count == 1;
    }

    @Override
    public Restaurant get(int id) {

        connection = dbUtil.getConnection();

        Restaurant restaurant = new Restaurant();

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from restaurants where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                restaurant.setRestaraunt_Id(rs.getInt("id"));
                restaurant.setName(rs.getString("name"));

            }
            restaurant.setMenu(getMenu(restaurant));



        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        log.info("get {}", id);

        return restaurant;
    }

    @Override
    public List<Restaurant> getAll() {


        connection = dbUtil.getConnection();

        List<Restaurant> restaurants = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from restaurants");
            while (rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaraunt_Id(rs.getInt("id"));
                restaurant.setName(rs.getString("name"));
                restaurants.add(restaurant);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        log.info("getAll");

        return restaurants;
    }
}