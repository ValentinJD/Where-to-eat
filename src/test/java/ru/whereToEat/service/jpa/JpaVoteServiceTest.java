package ru.whereToEat.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.service.AbstractVoteServiceTest;

import static ru.whereToEat.Profiles.JPA;

@ActiveProfiles(JPA)
class JpaVoteServiceTest extends AbstractVoteServiceTest {
}
