package ru.wheretoeat.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import ru.wheretoeat.VoteTestData;
import ru.wheretoeat.model.Vote;
import ru.wheretoeat.web.AbstractControllerTest;
import ru.wheretoeat.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.wheretoeat.TestUtil.userHttpBasic;
import static ru.wheretoeat.UserTestData.ADMIN;
import static ru.wheretoeat.web.vote.VoteRestController.REST_URL_VOTE;

public class VoteRestControllerTest extends AbstractControllerTest {

    @Test
    void voteAfter11oClock() {

        Vote vote = VoteTestData.getUpdatedAfter11oClock();

        assertThrows(NestedServletException.class, () ->
                perform(MockMvcRequestBuilders.post(REST_URL_VOTE)
                        .with(userHttpBasic(ADMIN))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.writeValue(vote)))
                        .andExpect(status().isNoContent())
        );
    }

    @Test
    void voteBefore11oClock() throws Exception {

        Vote vote = VoteTestData.getUpdatedBefore11oClockCount1();

        perform(MockMvcRequestBuilders.post(REST_URL_VOTE)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(vote)))
                .andExpect(status().isCreated());
    }
}