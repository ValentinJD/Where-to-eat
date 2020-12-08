package ru.whereToEat.service.springjdbc;

import org.junit.Ignore;
import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.model.Role;
import ru.whereToEat.model.User;
import ru.whereToEat.service.AbstractUserServiceTest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

import static ru.whereToEat.Profiles.SPRINGJDBC;

@ActiveProfiles(SPRINGJDBC)
public class SpringJdbcUserServiceTest extends AbstractUserServiceTest {

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
