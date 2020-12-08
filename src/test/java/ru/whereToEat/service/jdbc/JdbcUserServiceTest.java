package ru.whereToEat.service.jdbc;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.Role;
import ru.whereToEat.model.User;
import ru.whereToEat.service.AbstractUserServiceTest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertThrows;
import static ru.whereToEat.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {

    @Override
    public void duplicateMailCreate() {
        assertThrows(NotSaveOrUpdateException.class, () ->
                service.create(new User(null, "name", "user@yandex.ru", "Duplicate", true, LocalDateTime.now(), Role.USER)));
    }

    @Ignore
    @Override
    public void createWithException() throws Exception {
        validateRootCause(() -> service.create(new User(null, "  ", "mail@yandex.ru", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "  ", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "  ", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "password", true, LocalDateTime.now(), null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "password", true, null, Role.USER)), ConstraintViolationException.class);
    }
}
