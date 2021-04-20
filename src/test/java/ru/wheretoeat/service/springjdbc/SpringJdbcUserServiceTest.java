package ru.wheretoeat.service.springjdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.wheretoeat.service.AbstractUserServiceTest;

import static ru.wheretoeat.Profiles.SPRINGJDBC;

@ActiveProfiles(SPRINGJDBC)
class SpringJdbcUserServiceTest extends AbstractUserServiceTest {
}
