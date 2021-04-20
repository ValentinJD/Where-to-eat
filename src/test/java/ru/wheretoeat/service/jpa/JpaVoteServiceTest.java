package ru.wheretoeat.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.wheretoeat.service.AbstractVoteServiceTest;

import static ru.wheretoeat.Profiles.JPA;

@ActiveProfiles(JPA)
class JpaVoteServiceTest extends AbstractVoteServiceTest {
}
