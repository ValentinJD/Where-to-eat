package ru.whereToEat.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.Role;
import ru.whereToEat.model.User;
import ru.whereToEat.repository.UserRepository;
import ru.whereToEat.util.TimeUtil;
import ru.whereToEat.util.dbUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class JDBCUserRepository implements UserRepository {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private Connection connection;


    @Override
    public User save(User user) {
        connection = dbUtil.getConnection();

        PreparedStatement preparedStatement = null;

        if (user.isNew()) {

            try {
                preparedStatement = connection
                        .prepareStatement("insert into users(name,email,password) values (?, ?, ?)");
                // Parameters start with 1
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.executeUpdate();
                user.setId(getId(user));
                saveRole(user);

                log.info("save {}", user);

                return user;
            } catch (NotSaveOrUpdateException | SQLException t) {

                throw new NotSaveOrUpdateException();
            }

        } else {

            try {
                preparedStatement = connection
                        .prepareStatement("update users set name=?,email=?,password=?, enabled=?" +
                                "where id=?");
                // Parameters start with 1
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setBoolean(4, user.isEnabled());
                preparedStatement.setInt(5, user.getId());

                int count = preparedStatement.executeUpdate();

                saveRole(user);

                log.info("update {}", user);

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
    public boolean delete(Integer id) {

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

        log.info("delete {}", id);

        return count == 1;
    }

    @Override
    public User get(Integer id) {

        connection = dbUtil.getConnection();

        User user = new User();

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select users.*, roles.role" +
                            " from users, roles" +
                            " where users.id = roles.user_id and users.id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setEnabled(rs.getBoolean("enabled"));
                user.setRegistered(LocalDateTime.parse(TimeUtil.toDateFormatString(rs.getString("registered"))));
                user.setRole(Role.valueOf(rs.getString("role")));
            }

            log.info("get {}", user);

            if (user.getId() == null) {
                return null;
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return user;
    }


    private User createUser(ResultSet rs) {
        return null;
    }

    @Override
    public List<User> getAll() {

        log.info("getAll");

        connection = dbUtil.getConnection();

        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select users.*, roles.role" +
                    " from users, roles" +
                    " where users.id = roles.user_id");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setEnabled(rs.getBoolean("enabled"));
                user.setRegistered(LocalDateTime.parse(TimeUtil.toDateFormatString(rs.getString("registered"))));
                user.setRole(Role.valueOf(rs.getString("role")));
                users.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        users.sort(Comparator.comparing(User::getName));
        users.sort(Comparator.comparing(User::getEmail));
        return users;
    }

    @Override
    public User getByEmail(String email) {
        connection = dbUtil.getConnection();

        User user = null;

        try {
            user = new User();
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select *" +
                            " from users" +
                            " where users.email = ?");
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setEnabled(rs.getBoolean("enabled"));
                user.setRegistered(LocalDateTime.parse(TimeUtil.toDateFormatString(rs.getString("registered"))));
                user.setRole(getRole(user.getId()));
                saveRole(user);
            }

            log.info("getByEmail {}", user);

            if (user.getId() == null) {
                return null;
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return user;
    }

    private Role getRole(int userId) {
        return get(userId).getRole();
    }


    private void saveRole(User user) {

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


    private Integer getId(User user) throws NotFoundException {

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

        log.info("getId {}", id);

        return id;
    }
}
