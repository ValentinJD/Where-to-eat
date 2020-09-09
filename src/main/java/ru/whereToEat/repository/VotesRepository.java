package ru.whereToEat.repository;

import ru.whereToEat.model.User;
import ru.whereToEat.model.Vote;

import java.util.List;

public interface VotesRepository {
    // null if not found, when updated
    Vote save(Vote vote, int restaurantId);

    // false if not found
    boolean delete(int id);

    // NotFoundException if not found
    User get(int id);

    List<Vote> getAll(int restaurantId);

    boolean isNewVote(User user, int restaurantId);
}
