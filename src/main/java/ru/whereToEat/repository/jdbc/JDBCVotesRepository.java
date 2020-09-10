package ru.whereToEat.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.model.Role;
import ru.whereToEat.model.User;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.VotesRepository;
import ru.whereToEat.util.TimeUtil;
import ru.whereToEat.util.dbUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JDBCVotesRepository implements VotesRepository {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private Connection connection;

    @Override
    public Vote save(Vote vote, int restaurantId) {
        connection = dbUtil.getConnection();

        PreparedStatement preparedStatement = null;

        if (isNewVote(vote.getUserId(), restaurantId)) {

            try {
                preparedStatement = connection
                        .prepareStatement("insert into history_votes(user_id, restaurant_id, vote) " +
                                "values (?,?,?)");
                // Parameters start with 1
                preparedStatement.setInt(1, vote.getUserId());
                //preparedStatement.setTimestamp(2, new Timestamp(LocalDateTimeToLong(vote.getDate_vote())));
                preparedStatement.setInt(2, vote.getRestaurantId());
                preparedStatement.setInt(3, vote.getVote());
                preparedStatement.executeUpdate();

                log.info("save {}", vote);

                return vote;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {
            if (isVoteUserInRestaurantBefore11Hour(vote.getUserId(), restaurantId)) {
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

    private Boolean isVoteUserInRestaurantBefore11Hour(int userId, int restaurantId) {
        return true;
    }

    @Override
    public boolean delete(int voteId) {

        connection = dbUtil.getConnection();

        Integer count = null;

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("delete from history_votes where id=?");
            preparedStatement.setInt(1, voteId);
            count = preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        log.info("delete {}", voteId);

        return count == 1;
    }

    @Override
    public Vote get(int voteId) {
        connection = dbUtil.getConnection();

        Vote vote = new Vote();

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select  * " +
                            "from history_votes" +
                            " where history_votes.id = ?");
            preparedStatement.setInt(1, voteId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                vote.setId(rs.getInt("id"));
                vote.setUserId(rs.getInt("user_id"));
                vote.setDate_vote(LocalDateTime.parse(TimeUtil.toDateFormatString(rs.getString("date_vote"))));
                vote.setRestaurantId(rs.getInt("restaurant_id"));
                vote.setVote(rs.getInt("vote"));
            }

            if (vote.getId() == null) {
                return null;
            }

            log.info("get {}", voteId);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return vote;
    }

    @Override
    public List<Vote> getAll(int restaurantId) {
        connection = dbUtil.getConnection();

        List<Vote> votes = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select *" +
                    " from history_votes" +
                    " where history_votes.restaurant_id = restaurant_id");

            while (rs.next()) {
                Vote vote = new Vote();
                vote.setId(rs.getInt("id"));
                vote.setUserId(rs.getInt("user_id"));
                vote.setDate_vote(LocalDateTime.parse(TimeUtil.toDateFormatString(rs.getString("date_vote"))));
                vote.setRestaurantId(rs.getInt("restaurant_id"));
                vote.setVote(rs.getInt("vote"));
                votes.add(vote);
            }

            if (votes.isEmpty()) {
                return null;
            }

            log.info("getAll restaurantId {}", restaurantId);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return votes;
    }

    @Override
    public boolean isNewVote(int userId, int restaurantId) {
        return getAll(restaurantId).stream()
                .allMatch((vote) -> vote.getUserId() != userId && vote.getRestaurantId() != restaurantId);
    }
}
