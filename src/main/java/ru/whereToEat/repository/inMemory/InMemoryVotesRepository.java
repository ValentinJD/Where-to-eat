package ru.whereToEat.repository.inMemory;

import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.User;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.VotesRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryVotesRepository implements VotesRepository {
    static Map<Integer, Vote> storage = new HashMap<>();

    @Override
    public Vote save(Vote vote) throws NotFoundException, NotSaveOrUpdateException {
        return storage.put(vote.getId(), vote);
    }

    @Override
    public boolean delete(int userId, int restaurantId) throws NotFoundException {
        Vote vote1 = getAll(restaurantId).stream()
                .filter((vote -> vote.getUserId() == userId && vote.getRestaurantId() == restaurantId))
                .findFirst()
                .get();
        return storage.remove(vote1.getId()) != null;
    }

    @Override
    public Vote get(int userId, int restaurantId) throws NotFoundException {
        return getAll(restaurantId).stream()
                .filter((vote -> vote.getUserId() == userId))
                .findFirst()
                .get();
    }

    @Override
    public List<Vote> getAll(int restaurantId) throws NotFoundException {
        return storage.values().stream()
                .filter((vote)-> vote.getRestaurantId() == restaurantId)
                .collect(Collectors.toList());
    }
}
