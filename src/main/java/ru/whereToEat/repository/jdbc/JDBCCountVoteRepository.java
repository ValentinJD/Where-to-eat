package ru.whereToEat.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.CountVote;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.CountVoteRepository;
import ru.whereToEat.util.TimeUtil;
import ru.whereToEat.util.dbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JDBCCountVoteRepository implements CountVoteRepository {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private Connection connection;

    @Override
    public CountVote save(CountVote countVote) throws NotFoundException, NotSaveOrUpdateException {
        connection = dbUtil.getConnection();

        PreparedStatement preparedStatement = null;

        if (countVote.isNew()) {

            try {
                preparedStatement = connection
                        .prepareStatement("insert into count_votes(restaurant_id, count) values (?,?)");
                // Parameters start with 1
                preparedStatement.setInt(1, countVote.getRestaurantId());
                preparedStatement.setInt(2, countVote.getCount());
                preparedStatement.executeUpdate();

                log.info("save {}", countVote);

                return countVote;
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        } else {

            try {
                preparedStatement = connection
                        .prepareStatement("update count_votes set count=?" +
                                "where date = ? and restaurant_id=? ");
                // Parameters start with 1
                preparedStatement.setInt(1, countVote.getCount());
                Date date = Date.from(countVote.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                preparedStatement.setDate(2, sqlDate);
                preparedStatement.setInt(3, countVote.getRestaurantId());

                int count = preparedStatement.executeUpdate();

                if (count == 0) {
                    return null;
                } else {

                    log.info("update {}", countVote);
                    return countVote;
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public boolean delete(int countVoteId) {
        connection = dbUtil.getConnection();

        Integer count = null;

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("delete from count_votes where id=?");
            preparedStatement.setInt(1, countVoteId);
            count = preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        log.info("delete count_votesId {}", countVoteId);

        return count == 1;
    }

    @Override
    public CountVote get(int countVoteId) {
        connection = dbUtil.getConnection();

        CountVote countVote = new CountVote();

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from count_votes where id=?");
            preparedStatement.setInt(1, countVoteId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                countVote.setId(rs.getInt("id"));
                countVote.setRestaurantId(rs.getInt("restaurant_id"));
                countVote.setCount(rs.getInt("count"));
                Date input = rs.getDate("date");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                countVote.setDate(date);
            }


        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        log.info("get {}", countVoteId);

        return countVote;
    }

    @Override
    public List<CountVote> getAll(int restaurantId) {
        connection = dbUtil.getConnection();

        List<CountVote> countVotes = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from count_votes" +
                            " where count_votes.restaurant_id = ?");
            preparedStatement.setInt(1, restaurantId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                CountVote countVote = get(rs.getInt("id"));
                countVotes.add(countVote);
            }

            if (countVotes.isEmpty()) {
                log.info("Запись в базе количества голосов по данному ресторану в базе отсутствуют");
                return countVotes;
            }

            log.info("getAll restaurantId {}", restaurantId);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return countVotes;
    }


}
