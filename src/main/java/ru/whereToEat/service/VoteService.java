package ru.whereToEat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.exceptions.NotSupportOperationException;
import ru.whereToEat.exceptions.NotVoteException;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.RestaurantRepository;
import ru.whereToEat.repository.VotesRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class VoteService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    VotesRepository votesRepository;

    RestaurantRepository restaurantRepository;

    public VoteService(VotesRepository votesRepository, RestaurantRepository restaurantRepository) {
        this.votesRepository = votesRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Vote> getallbyrestarauntid(int restaurantId) {
        return votesRepository.getAll(restaurantId);
    }

    public List<Vote> getAll() {
        return votesRepository.getAllForTest();
    }


    public void delete(int voteId) {
        votesRepository.delete(voteId);
    }

    public Vote get(int voteId) {
        return votesRepository.get(voteId);
    }

    private Boolean isVoteUserInRestaurantBefore11Hour(Vote vote) throws NotVoteException {
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime voteDateTime = vote.getDate_vote();
        boolean isVote;
        if (ldt.toLocalDate().isEqual(voteDateTime.toLocalDate())) {
            int hour = vote.getDate_vote().getHour();
            isVote = hour < 11;
            log.info("isVoteUserInRestaurantBefore11Hour {}", isVote);
        } else {
            throw new NotVoteException("Голосовать нужно до 11 часов");
        }

        return isVote;
    }

    public int getCountVote(int restaurantId)  {
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

    public Vote vote(Vote vote) throws NotSaveOrUpdateException, NotVoteException, NotFoundException {
        log.info("vote {}", vote);

        if (isVoteUserInRestaurantBefore11Hour(vote)) {

            return votesRepository.save(vote);
        }
        throw new NotVoteException("голосование проходит только до 11 часов");
    }

    public void voter(int restaurantId, int userId, int countVote) throws NotFoundException, NotSaveOrUpdateException, NotVoteException {
        Vote vote;

        vote = votesRepository.getByRestaurantId(restaurantId);

        if (vote == null) {
            vote = new Vote();
        }

        if (vote.isNew()) {
            vote.setUserId(userId);
            vote.setRestaurantId(restaurantId);
            vote.setVote(countVote);
            vote.setDate_vote(LocalDateTime.now());
            votesRepository.save(vote);

        } else {
            if (isVoteUserInRestaurantBefore11Hour(vote)) {
                vote.setVote(countVote);
            } else {
                throw new NotVoteException("Голосовать необходимо до 11 часов");
            }

        }

        Restaurant restaurant = Objects.requireNonNull(restaurantRepository.get(restaurantId));
        votesRepository.save(vote);
        int countInRestaurant = getCountVote(restaurantId);
        restaurant.setVote_count(countInRestaurant);
        restaurantRepository.save(restaurant);
    }
}
