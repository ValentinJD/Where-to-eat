package ru.whereToEat.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.service.AbstractMealServiceTest;

import static ru.whereToEat.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest {
}
