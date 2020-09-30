package ru.whereToEat.web;

import org.springframework.beans.factory.annotation.Autowired;
import ru.whereToEat.model.Role;
import ru.whereToEat.service.UserService;

public class SecurityUtil {
    private static int admin = 100000;
    private static int user = 100001;

    @Autowired
    private static UserService userService;

    private static int currentUser = 0;

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
