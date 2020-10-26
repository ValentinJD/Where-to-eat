package ru.whereToEat;

import ru.whereToEat.model.Role;
import ru.whereToEat.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {

    public static final int ADMIN_ID = 100000;
    public static final int USER_ID = 100001;

    public static final User ADMIN = new User(100000, "Admin", "admin@mail.ru", "password",
            true, LocalDateTime.now(), Role.ADMIN);

    public static final User USER = new User(100001, "User", "user@mail.ru", "password",
            true, LocalDateTime.now(), Role.USER);

}
