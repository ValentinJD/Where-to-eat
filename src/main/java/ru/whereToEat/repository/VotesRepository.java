package ru.whereToEat.repository;

import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VotesRepository {
    // null if not found, when updated
    Vote save(Vote vote) throws NotFoundException, NotSaveOrUpdateException;

    // false if not found
    boolean delete(int voteId);

    // null if not found
    Vote get(int voteId);

    // empty list if not found
    //ordered by datetime
    List<Vote> getAll(int restaurantId);
    //ordered by datetime
    List<Vote> getAllForTest();

    // empty if not found
    //ordered by datetime
    List<Vote> getByRestaurantAndUserId(int restaurantId, int userId);

    Vote getByRestaurantIdUserIdAndLocalDate(int restaurantId, int userId, LocalDate ldt);
}
