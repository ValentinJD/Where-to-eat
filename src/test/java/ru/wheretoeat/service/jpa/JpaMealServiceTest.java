package ru.wheretoeat.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.wheretoeat.service.AbstractMealServiceTest;

import static ru.wheretoeat.Profiles.JPA;

@ActiveProfiles(JPA)
class JpaMealServiceTest extends AbstractMealServiceTest {
}
