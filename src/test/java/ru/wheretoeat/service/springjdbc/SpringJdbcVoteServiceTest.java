package ru.wheretoeat.service.springjdbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.wheretoeat.exceptions.NotFoundException;
import ru.wheretoeat.service.AbstractVoteServiceTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.wheretoeat.Profiles.SPRINGJDBC;
import static ru.wheretoeat.VoteTestData.VOTE_ADMIN_ID1_ON_PERCHINI;

@ActiveProfiles(SPRINGJDBC)
class SpringJdbcVoteServiceTest extends AbstractVoteServiceTest {

    @Override
    public void delete() {
        service.delete(VOTE_ADMIN_ID1_ON_PERCHINI);
        assertThrows(NotFoundException.class, () -> service.delete(VOTE_ADMIN_ID1_ON_PERCHINI));
    }


    @Test
    void getAll() {

    }

    @Test
    void getallbyrestarauntid() {

    }

    @Test
    void get() {

    }

    @Test

    void getByRestaurantIdUserIdAndLocalDate() {

    }

    @Test
    void getCountVote() {

    }


}
