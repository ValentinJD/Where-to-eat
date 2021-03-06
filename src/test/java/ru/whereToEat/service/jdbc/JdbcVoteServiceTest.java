package ru.whereToEat.service.jdbc;


import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.model.Vote;
import ru.whereToEat.service.AbstractVoteServiceTest;

import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.whereToEat.Profiles.JDBC;
import static ru.whereToEat.VoteTestData.VOTE_ADMIN_ID1_ON_PERCHINI;
import static ru.whereToEat.VoteTestData.assertMatch;

@ActiveProfiles(JDBC)
class JdbcVoteServiceTest extends AbstractVoteServiceTest {
    @Override
    public void delete() {
        service.delete(VOTE_ADMIN_ID1_ON_PERCHINI);
        assertThrows(NotFoundException.class, () -> service.delete(VOTE_ADMIN_ID1_ON_PERCHINI));
    }

    @Test
    public void getByRestaurantIdUserIdAndLocalDate() {
        Vote actual = service.getByRestaurantIdUserIdAndLOcalDate(100002, 100000, LocalDateTime.now());
        Vote expected = new Vote(100014, 100000, LocalDateTime.now(), 100002, 1);
        assertMatch(actual, expected);
    }
}
