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

    VotesRepository votesRepository;

    public VoteService(VotesRepository votesRepository) {
        this.votesRepository = votesRepository;
    }

    public List<Vote> getAllByRestarauntId(int restaurantId) throws NotFoundException {
        return votesRepository.getAll(restaurantId);
    }

    public Vote vote(Vote vote) throws NotSaveOrUpdateException, NotVoteException, NotFoundException {
        if (isVoteUserInRestaurantBefore11Hour(vote.getUserId(), vote.getRestaurantId())) {
            log.info("vote {}", vote);
            return votesRepository.save(vote);
        }
        throw new NotVoteException();
    }

    private Boolean isVoteUserInRestaurantBefore11Hour(int userId, int restaurantId) throws NotFoundException {

        Vote vote = votesRepository.get(userId, restaurantId);
        int time = vote.getDate_vote().getHour();
        log.info("isVoteUserInRestaurantBefore11Hour");
        return time < 11;
    }
}
