package ru.whereToEat.repository.inMemory;

import org.springframework.stereotype.Repository;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.VotesRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<Vote> getAll(int restaurantId) {
        List<Vote> listByRestaurantId = storage.values().stream()
                .filter((vote) -> vote.getRestaurantId() == restaurantId)
                .collect(Collectors.toList());
        return sortByDateTime(listByRestaurantId);
    }

    @Override
    public List<Vote> getAllForTest() {
        return sortByDateTime((List<Vote>) storage.values());
    }

    @Override
    public List<Vote> getByRestaurantAndUserId(int restaurantId, int userId) {
        List<Vote> list = storage.values().stream()
                .filter((vote) -> vote.getRestaurantId() == restaurantId)
                .filter(vote -> vote.getUserId() == userId)
                .collect(Collectors.toList());
        return sortByDateTime(list);
    }

    @Override
    public Vote getByRestaurantIdUserIdAndLocalDate(int restaurantId, int userId, LocalDate ldt) {
        return storage.values().stream()
                .filter((vote) -> vote.getRestaurantId() == restaurantId)
                .filter(vote -> vote.getUserId() == userId)
                .findFirst().orElse(null);
    }

    private List<Vote> sortByDateTime(List<Vote> list) {
        return list.stream()
                .sorted(Comparator.comparing(Vote::getDate_vote))
                .collect(Collectors.toList());
    }
}
