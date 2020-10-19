package ru.whereToEat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.whereToEat.model.Role;
import ru.whereToEat.service.UserService;

@Component
public class SecurityUtil {
    private static int admin = 100000;
    private static int user = 100001;

    private static UserService userService;

    private static int currentUser = admin;

    public SecurityUtil(UserService userService) {
        this.userService = userService;
    }

    public static int authUserId() {
        return currentUser;
    }

    public static void setUserId(int userId) {
        currentUser = userId;
    }

    public static boolean isAdmin(int userId) {
        return userService.get(userId).getRole().equals(Role.ADMIN);
    }
}
