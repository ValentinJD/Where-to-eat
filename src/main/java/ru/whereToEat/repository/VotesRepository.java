package ru.whereToEat.repository;

import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.User;
import ru.whereToEat.model.Vote;

import java.util.List;

public interface VotesRepository {
    // null if not found, when updated
    Vote save(Vote vote) throws NotFoundException, NotSaveOrUpdateException;

    // false if not found
    boolean delete(int userId, int restaurantId) throws NotFoundException;

    // NotFoundException if not found
    Vote get(int userId, int restaurantId)throws NotFoundException;

    List<Vote> getAll(int restaurantId) throws NotFoundException;


}
