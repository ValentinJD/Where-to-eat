package ru.whereToEat.util;

import ru.whereToEat.model.Role;
import ru.whereToEat.model.User;
import ru.whereToEat.to.UserTo;

public class UserUtil {
    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }
}
