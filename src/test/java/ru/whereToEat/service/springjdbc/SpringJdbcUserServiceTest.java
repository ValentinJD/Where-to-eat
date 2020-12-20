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
}
