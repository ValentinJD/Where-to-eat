package repository.jdbc;

import model.Role;
import model.User;
import repository.UserRepository;
import util.dbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class JDBCUserRepository implements UserRepository {
    @Override
    public User save(User user) {
        Connection connection = dbUtil.getConnection();

        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = connection
                    .prepareStatement("insert into users(name,email,password) values (?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            return user;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        Connection connection = dbUtil.getConnection();
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
                user.setEnabled(rs.getBoolean("enabled"));
                user.setRegistered(rs.getDate("registered"));
//                setRoles(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    /*private void insertRoles(User u) {
        Connection connection = dbUtil.getConnection();
        User user = u;
        Set<Role> roles = u.getRoles();

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from roles where user_id=?");
            preparedStatement.setInt(1, user.getUserId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUserId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setEnabled(rs.getBoolean("enabled"));
                user.setRegistered(rs.getDate("registered"));
                setRoles(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (!CollectionUtils.isEmpty(roles)) {
            jdbcTemplate.batchUpdate("INSERT INTO user_roles (user_id, role) VALUES (?, ?)", roles, roles.size(),
                    (ps, role) -> {
                        ps.setInt(1, u.getId());
                        ps.setString(2, role.name());
                    });
        }
    }

    private void deleteRoles(User u) {
        jdbcTemplate.update("DELETE FROM user_roles WHERE user_id=?", u.getId());
    }

    private User setRoles(User u) {
        if (u != null) {
            List<Role> roles = jdbcTemplate.queryForList("SELECT role FROM user_roles  WHERE user_id=?", Role.class, u.getId());
            u.setRoles(roles);
        }
        return u;
    }*/
}
