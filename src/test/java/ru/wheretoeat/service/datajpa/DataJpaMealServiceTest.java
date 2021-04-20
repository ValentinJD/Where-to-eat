package ru.wheretoeat.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.wheretoeat.exceptions.NotFoundException;
import ru.wheretoeat.service.AbstractMealServiceTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.wheretoeat.MealTestData.MEDALYONY_IZ_GOVYADINY_ID;
import static ru.wheretoeat.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
class DataJpaMealServiceTest extends AbstractMealServiceTest {

    @Override
    public void delete() {
        service.delete(MEDALYONY_IZ_GOVYADINY_ID);
        assertThrows(NotFoundException.class,
                () -> service.delete(MEDALYONY_IZ_GOVYADINY_ID));
    }
}
