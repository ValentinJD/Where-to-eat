package ru.whereToEat.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.service.AbstractRestaurantServiceTest;

import static ru.whereToEat.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaRestaurantServiceTest extends AbstractRestaurantServiceTest {
}
