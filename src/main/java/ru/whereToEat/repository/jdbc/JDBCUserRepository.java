package ru.whereToEat.repository.jdbc;

import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.model.Role;
import ru.whereToEat.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.whereToEat.repository.UserRepository;
import ru.whereToEat.util.dbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserRepository implements UserRepository {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private Connection connection;

    @Override
    public User save(User user) {
        connection = dbUtil.getConnection();

        PreparedStatement preparedStatement = null;

        if (user.isNew()) {

            log.info("save {}", user);

            try {
                preparedStatement = connection
                        .prepareStatement("insert into users(name,email,password) values (?, ?, ?)");
                // Parameters start with 1
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.executeUpdate();
                setRole(user);
                return user;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {

            log.info("update {}", user);

            try {
                preparedStatement = connection
                        .prepareStatement("update users set name=?,email=?,password=?, enabled=?" +
                                "where id=?");
                // Parameters start with 1
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setBoolean(4, user.isEnabled());
                preparedStatement.setInt(5, user.getUserId());

                int count = preparedStatement.executeUpdate();

                setRole(user);
                if (count == 0) {
                    return null;
                } else {
                    return user;
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public boolean delete(int id) {

        log.info("delete {}", id);

        connection = dbUtil.getConnection();

        Integer count = null;

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("delete from users where id=?");
            preparedStatement.setInt(1, id);
            count = preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        boolean b = count == 1;
        return b;

    }

    @Override
    public User get(int id) {

        log.info("get {}", id);

        connection = dbUtil.getConnection();

        User user = new User();

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from users where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUserId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword("password");
                user.setEnabled(rs.getBoolean("enabled"));
                user.setRegistered(rs.getDate("registered"));
            }
            user.setRole(getRole(user));

            if (user.getUserId() == null) {
                return null;
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> getAll() {

        log.info("getAll");

        connection = dbUtil.getConnection();

        List<User> users = new ArrayList<User>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setEnabled(rs.getBoolean("enabled"));
                user.setRegistered(rs.getDate("registered"));
                user.setRole(getRole(user));
                users.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

    private void setRole(User user) {

        connection = dbUtil.getConnection();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into roles(user_id, role) values (?, ?)");
            preparedStatement.setInt(1, getId(user));
            preparedStatement.setString(2, user.getRole().name());
            int count = preparedStatement.executeUpdate();
            log.info("setRole {} count {}", user.getRole(), count);
        } catch (SQLException | NotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private Role getRole(User user) {
        if (user == null) {
            return null;
        }

        log.info("getRole {}", user.getRole());

        connection = dbUtil.getConnection();

        Role role = null;

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from roles where user_id=?");
            preparedStatement.setInt(1, getId(user));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                role = Role.valueOf(rs.getString("role"));
            }

        } catch (SQLException | NotFoundException throwable) {
            throwable.printStackTrace();
        }

        return role;
    }

    private Integer getId(User user) throws NotFoundException {

        log.info("getId {}", user.getUserId());

        connection = dbUtil.getConnection();

        Integer id = null;

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from users where email=?");
            preparedStatement.setString(1, user.getEmail());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (id == null) {
            throw new NotFoundException("Пользователь с данным id в базе отсутствует");
        }

        return id;
    }
}
