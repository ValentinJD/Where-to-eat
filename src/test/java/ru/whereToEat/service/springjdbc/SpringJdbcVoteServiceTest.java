package ru.whereToEat.service.springjdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.service.AbstractVoteServiceTest;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertThrows;
import static ru.whereToEat.Profiles.SPRINGJDBC;
import static ru.whereToEat.VoteTestData.VOTE_ADMIN_ID1_ON_PERCHINI;

@ActiveProfiles(SPRINGJDBC)
public class SpringJdbcVoteServiceTest extends AbstractVoteServiceTest {

    @Override
    public void delete() {
        service.delete(VOTE_ADMIN_ID1_ON_PERCHINI);
        assertThrows(NotFoundException.class, ()-> service.delete(VOTE_ADMIN_ID1_ON_PERCHINI));
    }
}
