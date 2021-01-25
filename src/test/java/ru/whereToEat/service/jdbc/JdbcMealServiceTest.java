package ru.whereToEat.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.service.AbstractMealServiceTest;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.whereToEat.MealTestData.MEDALYONY_IZ_GOVYADINY_ID;
import static ru.whereToEat.Profiles.JDBC;

@ActiveProfiles(JDBC)
class JdbcMealServiceTest extends AbstractMealServiceTest {

    @Override
    public void delete() {
        service.delete(MEDALYONY_IZ_GOVYADINY_ID);
        assertThrows(NotFoundException.class,
                () -> service.delete(MEDALYONY_IZ_GOVYADINY_ID));
    }
}
