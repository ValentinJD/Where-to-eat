package ru.whereToEat.service.jdbc;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.service.AbstractMealServiceTest;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertThrows;
import static ru.whereToEat.MealTestData.MEDALYONY_IZ_GOVYADINY_ID;
import static ru.whereToEat.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcMealServiceTest extends AbstractMealServiceTest {

    @Override
    public void delete() {
        service.delete(MEDALYONY_IZ_GOVYADINY_ID);
        assertThrows(NotFoundException.class,
                () -> service.delete(MEDALYONY_IZ_GOVYADINY_ID));
    }
}
