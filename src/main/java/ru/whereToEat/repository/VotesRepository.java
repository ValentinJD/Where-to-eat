package ru.whereToEat.repository;

import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.model.User;
import ru.whereToEat.model.Vote;

import java.util.List;

public interface VotesRepository {
    // null if not found, when updated
    Vote save(Vote vote);

    // false if not found
    boolean delete(int id);

    // NotFoundException if not found
    Vote get(int voteId);

    List<Vote> getAll(int restaurantId) throws NotFoundException;

    boolean isNewVote(int userId, int restaurantId);
}
