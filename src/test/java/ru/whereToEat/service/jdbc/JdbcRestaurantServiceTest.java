package ru.whereToEat.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.RestaurantTestData;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.service.AbstractRestaurantServiceTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.whereToEat.Profiles.JDBC;

@ActiveProfiles(JDBC)
class JdbcRestaurantServiceTest extends AbstractRestaurantServiceTest {

    @Override
    public void delete() {
        service.delete(RestaurantTestData.PERCHINI_ID);
        assertThrows(NotFoundException.class, () -> service.delete(RestaurantTestData.PERCHINI_ID));
    }
}
