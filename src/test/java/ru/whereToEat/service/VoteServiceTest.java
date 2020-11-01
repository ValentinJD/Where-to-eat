package ru.whereToEat.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.whereToEat.TestMatcher;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.exceptions.NotVoteException;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Vote;

import static org.junit.Assert.*;
import static ru.whereToEat.MealTestData.*;
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
        assertNotNull(service.get(VOTE_ADMIN_ID1_ON_PERCHINI));
        service.delete(VOTE_ADMIN_ID1_ON_PERCHINI);
        assertNull(service.get(VOTE_ADMIN_ID1_ON_PERCHINI));
    }

    @Test
    public void get() {
        Vote expected = service.get(VOTE_ADMIN_ID1_ON_PERCHINI);
        TestMatcher<Vote> testMatcher = TestMatcher.usingFieldsComparator("date_vote");
        testMatcher.assertMatch(VOTE_ADMIN_ON_PERCHINI, expected);
    }


    @Test
    public void voteBefore11oClock() throws NotVoteException, NotSaveOrUpdateException {
        Vote before11 = getNewBefore11oClock();

        assertThrows(NotVoteException.class, ()->
                service.voter(before11.getRestaurantId(),before11.getUserId(), before11.getVote()));
    }

    /*@Test
    public void voteAfter11oClock() throws NotVoteException, NotSaveOrUpdateException {
        Vote after11 = getNewAfter11oClock();
                assertThrows(NotVoteException.class, ()->
                        service.voter(after11.getRestaurantId(),after11.getUserId(), after11.getVote()));
    }*/

    @Test
    public void voter() {

    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getallbyrestarauntid() {
    }

    @Test
    public void getByRestaurantIdUserIdAndLocalDate() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getCountVote() {
    }
}