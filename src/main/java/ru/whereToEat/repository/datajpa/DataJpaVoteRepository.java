package ru.whereToEat.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.VotesRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VotesRepository {

    private final CrudVoteRepository crudVoteRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository) {
        this.crudVoteRepository = crudVoteRepository;
    }

    @Override
    public Vote save(Vote vote) throws NotFoundException, NotSaveOrUpdateException {
        return crudVoteRepository.save(vote);
    }

    @Override
    public boolean delete(int voteId) {
        return crudVoteRepository.delete(voteId) != 0;
    }

    @Override
    public Vote get(int voteId) {
        return crudVoteRepository.findById(voteId).orElse(null);
    }

    @Override
    public List<Vote> getAll(int restaurantId) {
        return crudVoteRepository.findAllByRestaurantId(restaurantId);
    }

    @Override
    public List<Vote> getAllForTest() {
        return crudVoteRepository.findAll();
    }

    @Override
    public List<Vote> getByRestaurantAndUserId(int restaurantId, int userId) {
        return crudVoteRepository.findAllByRestaurantIdAndUserId(restaurantId, userId);
    }

    @Override
    public Vote getByRestaurantIdUserIdAndLocalDate(int restaurantId, int userId, LocalDate ldt) {
        return crudVoteRepository.
                getByRestaurantIdAndUserIdAndDate_vote(restaurantId, userId, ldt);
    }
}
