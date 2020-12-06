package ru.whereToEat.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.service.AbstractMealServiceTest;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertThrows;
import static ru.whereToEat.MealTestData.MEDALYONY_IZ_GOVYADINY_ID;
import static ru.whereToEat.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest {

    @Override
    public void delete() {
        service.delete(MEDALYONY_IZ_GOVYADINY_ID);
        assertThrows(NotFoundException.class,
                () -> service.delete(MEDALYONY_IZ_GOVYADINY_ID));
    }
}
