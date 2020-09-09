package ru.whereToEat.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.whereToEat.model.User;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.VotesRepository;
import ru.whereToEat.util.dbUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

public class JDBCVotesRepository implements VotesRepository {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private Connection connection;

    @Override
    public Vote save(Vote vote, int restaurantId) {
        connection = dbUtil.getConnection();

        PreparedStatement preparedStatement = null;

        if (isNewVote(vote.getUser(), restaurantId)) {

            try {
                preparedStatement = connection
                        .prepareStatement("insert into history_votes(user_id, restaurant_id, vote) " +
                                "values (?,?,?)");
                // Parameters start with 1
                preparedStatement.setInt(1, vote.getUser().getUserId());
                //preparedStatement.setTimestamp(2, new Timestamp(LocalDateTimeToLong(vote.getDate_vote())));
                preparedStatement.setInt(2, vote.getRestaurant().getRestaraunt_Id());
                preparedStatement.setInt(3,  vote.getVote());
                preparedStatement.executeUpdate();

                log.info("save {}", vote);

                return vote;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {
            if (isVoteUserInRestaurantBefore11Hour(vote.getUser(), restaurantId)) {
                try {
                    preparedStatement = connection
                            .prepareStatement("update history_votes set date_vote=?, vote=?" +
                                    "where id=?");

                    // Parameters start with 1
                    LocalDateTime ldt = vote.getDate_vote();
                    preparedStatement.setTimestamp(1, new Timestamp(LocalDateTimeToLong(ldt)),
                            Calendar.getInstance());
                    preparedStatement.setInt(2, vote.getVote());

                    int count = preparedStatement.executeUpdate();

                    if (count == 0) {
                        return null;
                    } else {

                        log.info("update {}", vote);
                        return vote;
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return null;
    }

    private long LocalDateTimeToLong(LocalDateTime ldt) {
        Date date = Date.valueOf(LocalDate.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth()));
        date.setHours(ldt.getHour());
        date.setMinutes(ldt.getSecond());
        date.setSeconds(ldt.getSecond());
        return date.getTime();
    }

    private Boolean isVoteUserInRestaurantBefore11Hour(User user, int restaurantId) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<Vote> getAll(int restaurantId) {
        return null;
    }

    @Override
    public boolean isNewVote(User user, int restaurantId) {
        return false;
    }
}
