package ru.whereToEat.service.springjdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.service.AbstractUserServiceTest;

import static ru.whereToEat.Profiles.SPRINGJDBC;

@ActiveProfiles(SPRINGJDBC)
class SpringJdbcUserServiceTest extends AbstractUserServiceTest {
}
