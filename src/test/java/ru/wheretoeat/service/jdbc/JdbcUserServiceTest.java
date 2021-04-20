package ru.wheretoeat.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.wheretoeat.exceptions.NotSaveOrUpdateException;
import ru.wheretoeat.model.Role;
import ru.wheretoeat.model.User;
import ru.wheretoeat.service.AbstractUserServiceTest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.wheretoeat.Profiles.JDBC;

@ActiveProfiles(JDBC)
class JdbcUserServiceTest extends AbstractUserServiceTest {
    @Override
    public void duplicateMailCreate() {
        assertThrows(NotSaveOrUpdateException.class, () ->
                service.create(new User(null, "name", "user@yandex.ru", "Duplicate", true, LocalDateTime.now(), Role.USER)));
    }

//    @Ignore
    @Override
    public void createWithException() throws Exception {
        validateRootCause(() -> service.create(new User(null, "  ", "mail@yandex.ru", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "  ", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "  ", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "password", true, LocalDateTime.now(), null)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "password", true, null, Role.USER)), ConstraintViolationException.class);
    }
}
