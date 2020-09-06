import ru.whereToEat.model.Role;
import ru.whereToEat.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final User USER = new User(100000, "User", "user@mail.ru", "password",
            true, LocalDateTime.now() , Role.USER);
    public static final User ADMIN = new User(100001, "Admin", "admin@mail.ru", "password",
            true, LocalDateTime.now(), Role.ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }

    public static void assertMatch(User actual) {
        assertThat(actual).isEqualTo(actual);

    }

}
