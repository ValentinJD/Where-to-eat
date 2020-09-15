package ru.whereToEat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.exceptions.NotVoteException;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.VotesRepository;

import java.util.List;

public class VoteService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    VotesRepository repository;

    public VoteService(VotesRepository repository) {
        this.repository = repository;
    }

    public List<Vote> getallbyrestarauntid(int restaurantId) throws NotFoundException {
        return repository.getAll(restaurantId);
    }

    public Vote vote(Vote vote) throws NotSaveOrUpdateException, NotVoteException, NotFoundException {
        if (isVoteUserInRestaurantBefore11Hour(vote.getUserId(), vote.getRestaurantId())) {
            log.info("vote {}", vote);
            return repository.save(vote);
        }
        throw new NotVoteException();
    }

    public void delete(int userId, int restaurantId) throws NotFoundException {
        repository.delete(userId, restaurantId);
    }

    public Vote get(int userId, int restaurantId) throws NotFoundException {
        return repository.get(userId, restaurantId);
    }

    private Boolean isVoteUserInRestaurantBefore11Hour(int userId, int restaurantId) throws NotFoundException {

        Vote vote = repository.get(userId, restaurantId);
        int time = vote.getDate_vote().getHour();
        log.info("isVoteUserInRestaurantBefore11Hour");
        return time < 11;
    }

    public int getCountVote(int restaurantId) throws NotFoundException {
        List<Vote> voteList = getallbyrestarauntid(restaurantId);
        int count = 0;
        for (Vote vote : voteList) {
            if (vote.getVote() > 0) {
                count++;
            }
            if (vote.getVote() < 0) {
                count--;
            }
        }
        return count;
    }
}