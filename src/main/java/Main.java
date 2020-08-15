import model.Role;
import model.User;
import repository.UserRepository;
import repository.jdbc.JDBCUserRepository;
import util.dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args)  {
        UserRepository userRepository = new JDBCUserRepository();
        /*Set<Role> set = new HashSet<>();
        set.add(Role.USER);
        User user = new User("юзер","email@ya.ru","password", set);
        userRepository.save(user);*/
        User user1 = userRepository.get(3);
        System.out.println(user1);
    }
}
