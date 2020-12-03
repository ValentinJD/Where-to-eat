package ru.whereToEat.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.service.AbstractRestaurantServiceTest;

import static ru.whereToEat.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaRestaurantServiceTest extends AbstractRestaurantServiceTest {
}
