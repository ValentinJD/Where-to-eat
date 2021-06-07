package ru.wheretoeat;

import ru.wheretoeat.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.wheretoeat.RestaurantTestData.*;
import static ru.wheretoeat.UserTestData.ADMIN_ID;
import static ru.wheretoeat.UserTestData.USER_ID;
import static ru.wheretoeat.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final TestMatcher<Vote> VOTE_TEST_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Vote.class, "date_vote");

    public static final int NOT_FOUND = 10;
    public static final int VOTE_ADMIN_ID1_ON_PERCHINI = START_SEQ + 14;
    public static final int VOTE_ADMIN_ID2_ON_BAR_AND_GRIL = START_SEQ + 15;
    public static final int VOTE_ADMIN_ID3_ON_TRI_OLENYA = START_SEQ + 16;
    public static final int VOTE_USER_ID1_ON_PERCHINI = START_SEQ + 17;
    public static final int VOTE_USER_ID2_ON_BAR_AND_GRIL = START_SEQ + 18;
    public static final int VOTE_USER_ID3_ON_TRI_OLENYA = START_SEQ + 19;

    public static final Vote VOTE_ADMIN_ON_PERCHINI =
            new Vote(VOTE_ADMIN_ID1_ON_PERCHINI, ADMIN_ID, LocalDateTime.now(), PERCHINI_ID, 1);
    public static final Vote VOTE_ADMIN_ON_BAR_AND_GRIL =
            new Vote(VOTE_ADMIN_ID2_ON_BAR_AND_GRIL, ADMIN_ID, LocalDateTime.now(), BAR_AND_GRIL_ID, 1);
    public static final Vote VOTE_ADMIN_ON_TRI_OLENYA =
            new Vote(VOTE_ADMIN_ID3_ON_TRI_OLENYA, ADMIN_ID, LocalDateTime.now(), TRI_OLENYA_ID, -1);

    public static final List<Vote> ADMIN_VOTES = Arrays.asList(
            VOTE_ADMIN_ON_PERCHINI, VOTE_ADMIN_ON_BAR_AND_GRIL, VOTE_ADMIN_ON_TRI_OLENYA);

    public static final Vote VOTE_USER_ON_PERCHINI =
            new Vote(VOTE_USER_ID1_ON_PERCHINI, USER_ID, LocalDateTime.now(), PERCHINI_ID, -1);
    public static final Vote VOTE_USER_ON_BAR_AND_GRIL =
            new Vote(VOTE_USER_ID2_ON_BAR_AND_GRIL, USER_ID, LocalDateTime.now(), BAR_AND_GRIL_ID, -1);
    public static final Vote VOTE_USER_ON_TRI_OLENYA =
            new Vote(VOTE_USER_ID3_ON_TRI_OLENYA, USER_ID, LocalDateTime.now(), TRI_OLENYA_ID, -1);

    public static final List<Vote> USER_VOTES = Arrays.asList(
            VOTE_USER_ON_PERCHINI, VOTE_USER_ON_BAR_AND_GRIL, VOTE_USER_ON_TRI_OLENYA);

    public static final List<Vote> VOTES_ON_PERCHINI = Arrays.asList(
            VOTE_ADMIN_ON_PERCHINI, VOTE_USER_ON_PERCHINI);

    public static final List<Vote> ALL_VOTES = Arrays.asList(
            VOTE_ADMIN_ON_PERCHINI, VOTE_ADMIN_ON_BAR_AND_GRIL, VOTE_ADMIN_ON_TRI_OLENYA,
            VOTE_USER_ON_PERCHINI, VOTE_USER_ON_BAR_AND_GRIL, VOTE_USER_ON_TRI_OLENYA
    );

    public final static String notValidVote =
            "{\n" +
                    "  \"date_vote\": \"2021-05-13T05:41:50.142Z\"\n" +
                    "  \n" +
                    "  \"restaurantId\": 100002,\n" +
                    "  \"userId\": 100000,\n" +
                    "  \"vote\": 1\n" +
                    "}";

    public static String getNotValidVote() {
        return notValidVote;
    }

    public static Vote getVoteAdminOnPerchiniToday() {
        return new Vote(VOTE_ADMIN_ID1_ON_PERCHINI, ADMIN_ID, LocalDateTime.now(), PERCHINI_ID, 1);
    }

    public static Vote getNewBefore11oClock() {
        return new Vote(null, ADMIN_ID, LocalDateTime.of(LocalDate.now(), LocalTime.of(01, 00)), PERCHINI_ID, 0);
    }

    public static Vote getNewAfter11oClock() {
        return new Vote(null, ADMIN_ID, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 00)), PERCHINI_ID, 0);
    }

    public static Vote getUpdatedAfter11oClock() {
        Vote updated = new Vote(VOTE_ADMIN_ID1_ON_PERCHINI, ADMIN_ID, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 00)), PERCHINI_ID, 0);
        updated.setVote(1);
        return updated;
    }

    public static Vote getUpdatedBefore11oClock() {
        Vote updated = new Vote(VOTE_ADMIN_ID1_ON_PERCHINI, ADMIN_ID, LocalDateTime.of(LocalDate.now(), LocalTime.of(01, 00)), PERCHINI_ID, 0);
        updated.setVote(1);
        return updated;
    }

    public static Vote getUpdatedBefore11oClockCount1() {
        Vote updated = new Vote(VOTE_ADMIN_ID1_ON_PERCHINI, ADMIN_ID, LocalDateTime.of(LocalDate.now(), LocalTime.of(01, 00)), PERCHINI_ID, 1);
        updated.setVote(1);
        return updated;
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "date_vote");
    }
}
