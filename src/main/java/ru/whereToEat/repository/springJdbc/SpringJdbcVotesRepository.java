package ru.whereToEat.repository.springJdbc;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.VotesRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
public class SpringJdbcVotesRepository implements VotesRepository {

    private static final BeanPropertyRowMapper<Vote> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Vote.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;

    public SpringJdbcVotesRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("history_votes")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Vote save(Vote vote) throws NotFoundException, NotSaveOrUpdateException {
        Objects.nonNull(vote);
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", vote.getId())
                .addValue("user_id", vote.getUserId())
                .addValue("date_vote", vote.getDate_vote())
                .addValue("restaurant_id", vote.getRestaurantId())
                .addValue("vote", vote.getVote());


        if (vote.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(map);
            vote.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE history_votes SET user_id=:user_id, date_vote=:date_vote," +
                        " restaurant_id=:restaurant_id, vote=:vote" +
                        " WHERE id=:id", map) == 0) {
            return null;
        }
        return vote;
    }

    @Override
    public boolean delete(int voteId) {
        return jdbcTemplate.update("DELETE FROM history_votes WHERE id=?", voteId) != 0;
    }

    @Override
    public Vote get(int voteId) {
        List<Vote> votes = jdbcTemplate.query("SELECT * FROM history_votes WHERE id=?", ROW_MAPPER, voteId);
        return DataAccessUtils.singleResult(votes);
    }

    @Override
    public List<Vote> getAll(int restaurantId) {
        return jdbcTemplate.query("SELECT * FROM history_votes where restaurant_id=? Order By id", ROW_MAPPER, restaurantId);
    }

    @Override
    public List<Vote> getAllForTest() {
        return jdbcTemplate.query("SELECT * FROM history_votes Order By id", ROW_MAPPER);
    }

    @Override
    public List<Vote> getByRestaurantAndUserId(int restaurantId, int userId) {
        return jdbcTemplate.query("SELECT * FROM history_votes where restaurant_id=? and user_id=?" +
                " Order By id", ROW_MAPPER, restaurantId, userId);
    }

    @Override
    public Vote getByRestaurantIdUserIdAndLocalDate(int restaurantId, int userId, LocalDateTime ldt) {
/*        List<Vote> votes = jdbcTemplate.query("SELECT * FROM history_votes where restaurant_id=? and user_id=?" +
                "and date_vote=?" +
                " Order By id", ROW_MAPPER, restaurantId, userId, ldt);
        return DataAccessUtils.singleResult(votes);*/
        return getAll(restaurantId).stream()
                .filter(vote -> vote.getUserId() == userId)
                .filter(vote -> vote.getDate_vote().toLocalDate().isEqual(LocalDate.now()))
                .findFirst().orElse(null);
    }
}
