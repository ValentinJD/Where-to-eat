package ru.whereToEat.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.service.AbstractUserServiceTest;

import static ru.whereToEat.Profiles.JDBC;
import static ru.whereToEat.Profiles.JPA;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}
