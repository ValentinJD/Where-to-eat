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
    public boolean delete(int voteId) throws NotFoundException {
        Vote vote1 = getAll(voteId).stream()
                .filter((vote -> vote.getId() == voteId ))
                .findFirst()
                .get();
        return storage.remove(vote1.getId()) != null;
    }

    @Override
    public Vote get(int voteId) throws NotFoundException {
        return getAll(0).stream()
                .filter((vote -> vote.getId() == voteId))
                .findFirst()
                .get();
    }

    @Override
    public List<Vote> getAll(int o) throws NotFoundException {
        return (List<Vote>) storage.values();
    }

    @Override
    public List<Vote> getAllForTest() {
        return (List<Vote>) storage.values();
    }
}
