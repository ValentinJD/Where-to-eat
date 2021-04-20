package ru.wheretoeat.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.wheretoeat.VoteTestData;
import ru.wheretoeat.exceptions.NotSaveOrUpdateException;
import ru.wheretoeat.exceptions.NotVoteException;
import ru.wheretoeat.model.Vote;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.wheretoeat.MealTestData.PERCHINI_ID;
import static ru.wheretoeat.MealTestData.TRI_OLENYA_ID;
import static ru.wheretoeat.UserTestData.ADMIN_ID;
import static ru.wheretoeat.VoteTestData.*;

abstract public class AbstractVoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoteService service;

    @Test
    public void delete() {
        service.delete(VOTE_ADMIN_ID1_ON_PERCHINI);
        assertThrows(EntityNotFoundException.class, () -> service.delete(VOTE_ADMIN_ID1_ON_PERCHINI));
    }

    @Test
    void get() {
        Vote expected = service.get(VOTE_ADMIN_ID1_ON_PERCHINI);
        VOTE_TEST_MATCHER.assertMatch(VOTE_ADMIN_ON_PERCHINI, expected);
    }

    @Test
    void getVoteExceptionVoteAfter11oClock() throws NotVoteException, NotSaveOrUpdateException {
        Vote after11 = getNewAfter11oClock();
        assertThrows(NotVoteException.class, () -> service.voter(after11));
    }

    @Test
    void getCountVote() {
        assertEquals(0, service.getCountVote(PERCHINI_ID));
        assertEquals(-2, service.getCountVote(TRI_OLENYA_ID));
    }

    @Test
    void create() throws NotSaveOrUpdateException {
        //Vote actual = VoteTestData.getNewBefore11oClock();
        //Vote vote = service.create(VoteTestData.getNewBefore11oClock());
        Vote actual = VoteTestData.getNewAfter11oClock();
        Vote vote = service.create(VoteTestData.getNewAfter11oClock());
        int id = vote.getId();
        actual.setId(id);
        VOTE_TEST_MATCHER.assertMatch(actual, vote);
        /*testMatcher.assertMatch(actual, service.getByRestaurantIdUserIdAndLOcalDate(
                vote.getRestaurantId(), vote.getUserId(), vote.getDate_vote().toLocalDate()
        ));*/
    }

    @Test
    void update() throws NotSaveOrUpdateException {
        Vote actual = VoteTestData.getUpdatedAfter11oClock();
        Vote updated = service.update(VoteTestData.getUpdatedAfter11oClock());
        Integer id = updated.getId();
        actual.setId(id);
        VOTE_TEST_MATCHER.assertMatch(actual, updated);
        //testMatcher.assertMatch(actual, service.get(id));
    }

    @Test
    void getallbyrestarauntid() {
        List<Vote> expected = service.getallbyrestarauntid(PERCHINI_ID);
        List<Vote> actual = VOTES_ON_PERCHINI;
        VOTE_TEST_MATCHER.assertMatch(actual, expected);
    }


    @Test
    void getByRestaurantIdUserIdAndLocalDate() {
        Vote expected = service.getByRestaurantIdUserIdAndLOcalDate(PERCHINI_ID, ADMIN_ID, LocalDateTime.now());
        Vote actual = VoteTestData.getVoteAdminOnPerchiniToday();
        VOTE_TEST_MATCHER.assertMatch(actual, expected);
    }

    @Test
    void getAll() {
        List<Vote> actual = service.getAll();
        List<Vote> expected = ALL_VOTES;
        VOTE_TEST_MATCHER.assertMatch(actual, expected);
    }
}
