package ru.whereToEat.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.Role;
import ru.whereToEat.model.User;
import ru.whereToEat.service.AbstractUserServiceTest;

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
}
