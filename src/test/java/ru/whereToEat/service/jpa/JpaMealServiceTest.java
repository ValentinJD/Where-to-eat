package ru.whereToEat.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.service.AbstractMealServiceTest;

import static ru.whereToEat.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaMealServiceTest extends AbstractMealServiceTest {
}
