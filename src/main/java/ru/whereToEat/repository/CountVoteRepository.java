package ru.whereToEat.repository;

import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.CountVote;
import ru.whereToEat.model.Vote;

import java.util.List;

public interface CountVoteRepository {

    // null if not found, when updated
    CountVote save(CountVote countVote) throws NotFoundException, NotSaveOrUpdateException;

    // false if not found
    boolean delete(int countVoteId);

    // null if not found
    CountVote get(int restaurantId);

    // empty list if not found
    List<CountVote> getAll(int restaurantId);


    List<CountVote> getAll();
}
