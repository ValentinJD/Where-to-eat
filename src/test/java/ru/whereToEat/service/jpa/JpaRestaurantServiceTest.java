package ru.whereToEat.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.service.AbstractRestaurantServiceTest;

import static ru.whereToEat.Profiles.JPA;

@ActiveProfiles(JPA)
class JpaRestaurantServiceTest extends AbstractRestaurantServiceTest {
}
