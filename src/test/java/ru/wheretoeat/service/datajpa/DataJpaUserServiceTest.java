package ru.wheretoeat.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.wheretoeat.service.AbstractUserServiceTest;

import static ru.wheretoeat.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
class DataJpaUserServiceTest extends AbstractUserServiceTest {
}
