package ru.whereToEat.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.whereToEat.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.whereToEat.RestaurantTestData.PERCHINI_ID;
import static ru.whereToEat.web.vote.VoteRestController.REST_URL_VOTE;

public class VoteRestControllerTest extends AbstractControllerTest {
    @Test
    void voteAfter11oClock() throws Exception {

  /*      Vote vote = VoteTestData.getUpdatedAfter11oClock();

        perform(MockMvcRequestBuilders.put(REST_URL_VOTE + '/' + PERCHINI_ID + "/1"))

                .);*/
        //RESTAURANT_MATCHER.assertMatch(service.get(PERCHINI_ID), updated);
    }

    @Test
    void voteBefore11oClock() throws Exception {

        //Vote vote = VoteTestData.getUpdatedBefore11oClock();

        perform(MockMvcRequestBuilders.put(REST_URL_VOTE + '/' + PERCHINI_ID + "/1"))
                .andExpect(status().isNoContent());
        //RESTAURANT_MATCHER.assertMatch(service.get(PERCHINI_ID), updated);
    }
}
