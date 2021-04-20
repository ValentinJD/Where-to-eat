package ru.wheretoeat.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.wheretoeat.exceptions.NotFoundException;
import ru.wheretoeat.service.AbstractVoteServiceTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.wheretoeat.Profiles.DATAJPA;
import static ru.wheretoeat.VoteTestData.VOTE_ADMIN_ID1_ON_PERCHINI;

@ActiveProfiles(DATAJPA)
class DataJpaVoteServiceTest extends AbstractVoteServiceTest {

    @Override
    public void delete() {
        service.delete(VOTE_ADMIN_ID1_ON_PERCHINI);
        assertThrows(NotFoundException.class, ()-> service.delete(VOTE_ADMIN_ID1_ON_PERCHINI));
    }
}
