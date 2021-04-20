package ru.wheretoeat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.wheretoeat.AuthorizedUser;
import ru.wheretoeat.model.Role;
import ru.wheretoeat.service.UserService;

import static java.util.Objects.requireNonNull;

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
        return get().getUserTo().id();
    }

    public static void setUserId(int userId) {
        currentUser = userId;
        log.info("Login user {}", userId);
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        return requireNonNull(safeGet(), "No authorized user found");
    }


    public static String getUserName() {
        return userService.get(currentUser).getName();
    }

    public static boolean isAdmin(int userId) {
        return userService.get(userId).getRole().equals(Role.ADMIN);
    }
}
