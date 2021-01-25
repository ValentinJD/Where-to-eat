package ru.whereToEat.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.service.AbstractUserServiceTest;

import static ru.whereToEat.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
class DataJpaUserServiceTest extends AbstractUserServiceTest {
}
