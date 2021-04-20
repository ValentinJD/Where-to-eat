package ru.wheretoeat.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.wheretoeat.RestaurantTestData;
import ru.wheretoeat.exceptions.NotFoundException;
import ru.wheretoeat.service.AbstractRestaurantServiceTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.wheretoeat.Profiles.JDBC;

@ActiveProfiles(JDBC)
class JdbcRestaurantServiceTest extends AbstractRestaurantServiceTest {

    @Override
    public void delete() {
        service.delete(RestaurantTestData.PERCHINI_ID);
        assertThrows(NotFoundException.class, () -> service.delete(RestaurantTestData.PERCHINI_ID));
    }
}
