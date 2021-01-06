package ru.whereToEat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.whereToEat.model.Role;
import ru.whereToEat.model.User;
import ru.whereToEat.service.UserService;

@Component
public class SecurityUtil {
    private static int admin = 100000;
    private static int user = 100001;

    protected static final Logger log = LoggerFactory.getLogger(SecurityUtil.class);

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
        log.info("Login user {}", userId);
    }

    public static String getUserName() {
        return userService.get(currentUser).getName();
    }

    public static boolean isAdmin(int userId) {
        return userService.get(userId).getRole().equals(Role.ADMIN);
    }
}
