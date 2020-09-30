package ru.whereToEat.repository.inMemory;

import org.springframework.stereotype.Repository;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.VotesRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryVotesRepository implements VotesRepository {
    static Map<Integer, Vote> storage = new HashMap<>();

    @Override
    public Vote save(Vote vote) {
        return storage.put(vote.getId(), vote);
    }

    @Override
    public boolean delete(int voteId) {
        Vote vote1 = getAll(voteId).stream()
                .filter((vote -> vote.getId() == voteId))
                .findFirst()
                .get();
        return storage.remove(vote1.getId()) != null;
    }

    @Override
    public Vote get(int voteId) {
        return getAll(0).stream()
                .filter((vote -> vote.getId() == voteId))
                .findFirst()
                .get();
    }

    @Override
    public List<Vote> getAll(int o) {
        return (List<Vote>) storage.values();
    }

    @Override
    public List<Vote> getAllForTest() {
        return (List<Vote>) storage.values();
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
