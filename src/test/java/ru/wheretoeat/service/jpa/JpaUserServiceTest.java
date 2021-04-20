package ru.wheretoeat.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.wheretoeat.service.AbstractUserServiceTest;

import static ru.wheretoeat.Profiles.JPA;

@ActiveProfiles(JPA)
class JpaUserServiceTest extends AbstractUserServiceTest {
}
