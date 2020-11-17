package ru.whereToEat.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.whereToEat.TestMatcher;
import ru.whereToEat.VoteTestData;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.exceptions.NotVoteException;
import ru.whereToEat.model.Vote;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static ru.whereToEat.MealTestData.PERCHINI_ID;
import static ru.whereToEat.MealTestData.TRI_OLENYA_ID;
import static ru.whereToEat.UserTestData.ADMIN_ID;
import static ru.whereToEat.VoteTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class VoteServiceTest {

    @Autowired
    VoteService service;

    @Test
    public void delete() {
        service.delete(VOTE_ADMIN_ID1_ON_PERCHINI);
        assertThrows(EntityNotFoundException.class, ()-> service.delete(VOTE_ADMIN_ID1_ON_PERCHINI));
    }

    @Test
    public void get() {
        Vote expected = service.get(VOTE_ADMIN_ID1_ON_PERCHINI);
        TestMatcher<Vote> testMatcher = TestMatcher.usingFieldsComparator("date_vote");
        testMatcher.assertMatch(VOTE_ADMIN_ON_PERCHINI, expected);
    }


    @Test
    public void getVoteExceptionVoteAfter11oClock() throws NotVoteException, NotSaveOrUpdateException {
        Vote after11 = getNewAfter11oClock();
        assertThrows(NotVoteException.class, () -> service.voter(after11));
    }

    @Test
    public void getCountVote() {
        assertEquals(0, service.getCountVote(PERCHINI_ID));
        assertEquals(-2, service.getCountVote(TRI_OLENYA_ID));
    }

    @Test
    public void create() throws NotSaveOrUpdateException {
        Vote actual = VoteTestData.getNewBefore11oClock();
        Vote vote = service.create(VoteTestData.getNewBefore11oClock());
        int id = vote.id();
        actual.setId(id);
        TestMatcher<Vote> testMatcher = TestMatcher.usingFieldsComparator("date_vote");
        testMatcher.assertMatch(actual, vote);
        /*testMatcher.assertMatch(actual, service.getByRestaurantIdUserIdAndLOcalDate(
                vote.getRestaurantId(), vote.getUserId(), vote.getDate_vote().toLocalDate()
        ));*/
    }

    @Test
    public void update() throws NotSaveOrUpdateException {
        Vote actual = VoteTestData.getUpdatedAfter11oClock();
        Vote updated = service.update(VoteTestData.getUpdatedAfter11oClock());
        Integer id = updated.getId();
        actual.setId(id);
        TestMatcher<Vote> testMatcher = TestMatcher.usingFieldsComparator("date_vote");
        testMatcher.assertMatch(actual, updated);
        //testMatcher.assertMatch(actual, service.get(id));
    }

    @Test
    public void getallbyrestarauntid() {
        List<Vote> expected = service.getallbyrestarauntid(PERCHINI_ID);
        List<Vote> actual = VOTES_ON_PERCHINI;
        TestMatcher<Vote> testMatcher = TestMatcher.usingFieldsComparator("date_vote");
        testMatcher.assertMatch(actual, expected);
    }

    @Ignore
    @Test
    public void getByRestaurantIdUserIdAndLocalDate() {
        Vote expected = service.getByRestaurantIdUserIdAndLOcalDate(PERCHINI_ID, ADMIN_ID, LocalDate.now());
        Vote actual = VoteTestData.getVoteAdminOnPerchiniToday();
        TestMatcher<Vote> testMatcher = TestMatcher.usingFieldsComparator("date_vote");
        testMatcher.assertMatch(actual, expected);
    }

    @Test
    public void getAll() {
        List<Vote> actual = service.getAll();
        List<Vote> expected = ALL_VOTES;
        TestMatcher<Vote> testMatcher = TestMatcher.usingFieldsComparator("date_vote");
        testMatcher.assertMatch(actual, expected);
    }


}