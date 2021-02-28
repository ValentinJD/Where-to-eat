package ru.whereToEat;

import ru.whereToEat.model.Role;
import ru.whereToEat.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.whereToEat.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(User.class, "registered");
    public static final int NOT_FOUND = 10;
    public static final int ADMIN_ID = START_SEQ;
    public static final int  USER_ID = START_SEQ + 1;

    public static final User ADMIN = new User(100000, "Admin", "admin@gmail.com", "password",
            true, LocalDateTime.now(), Role.ADMIN);

    public static final User USER = new User(100001, "User", "user@yandex.ru", "password",
            true, LocalDateTime.now(), Role.USER);


    public static User getNew() {
        return new User(null, "NewName", "new@gmail.com", "newPassword", true,  LocalDateTime.now(), Role.USER);
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        return updated;
    }

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "role");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "role").isEqualTo(expected);
    }

}
