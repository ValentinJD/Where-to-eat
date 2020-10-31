package ru.whereToEat.repository.springJdbc;

import org.springframework.stereotype.Repository;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.VotesRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class SpringJdbcVotesRepository implements VotesRepository {
    @Override
    public Vote save(Vote vote) throws NotFoundException, NotSaveOrUpdateException {
        return null;
    }

    @Override
    public boolean delete(int voteId) {
        return false;
    }

    @Override
    public Vote get(int voteId) {
        return null;
    }

    @Override
    public List<Vote> getAll(int restaurantId) {
        return null;
    }

    @Override
    public List<Vote> getAllForTest() {
        return null;
    }

    @Override
    public List<Vote> getByRestaurantAndUserId(int restaurantId, int userId) {
        return null;
    }

    @Override
    public Vote getByRestaurantIdUserIdAndLocalDate(int restaurantId, int userId, LocalDate ldt) {
        return null;
    }
}
