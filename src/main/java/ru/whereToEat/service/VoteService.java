package ru.whereToEat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.exceptions.NotVoteException;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.RestaurantRepository;
import ru.whereToEat.repository.VotesRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.whereToEat.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    VotesRepository votesRepository;

    RestaurantRepository restaurantRepository;

    public VoteService(VotesRepository votesRepository, RestaurantRepository restaurantRepository) {
        this.votesRepository = votesRepository;
        this.restaurantRepository = restaurantRepository;
    }

   // @Cacheable("votes")
    public List<Vote> getallbyrestarauntid(int restaurantId) {

        return votesRepository.getAll(restaurantId).stream()
                .filter(vote -> vote.getDate_vote().toLocalDate().isEqual(LocalDate.now()))
                .sorted(Comparator.comparing(Vote::getId))
                .collect(Collectors.toList());
    }

   // @Cacheable("votes")
    public Vote getByRestaurantIdUserIdAndLOcalDate(int restaurantId, int userId, LocalDateTime ldt) {
        return votesRepository.getByRestaurantIdUserIdAndLocalDate(restaurantId, userId, ldt);
    }

   // @Cacheable("votes")
    public List<Vote> getAll() {
        return votesRepository.getAllForTest();
    }


    public void delete(int voteId) {
        checkNotFoundWithId(votesRepository.delete(voteId), voteId);
    }

    public Vote get(int voteId) {
        return checkNotFoundWithId(votesRepository.get(voteId), voteId);
        //return votesRepository.get(voteId);
    }

    private boolean isVoteUserInRestaurantBefore11Hour(Vote vote) {
        Objects.requireNonNull(vote);
//        return vote.getDate_vote().getHour() < 23;
        return true;
    }

    public int getCountVote(int restaurantId) {
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

    public void voter(Vote vote1) throws NotFoundException, NotSaveOrUpdateException, NotVoteException {
        Assert.notNull(vote1, "vote must not be null");
        int restaurantId = vote1.getRestaurantId();
        int userId = vote1.getUserId();
        int countVote = vote1.getVote();

        if (isVoteUserInRestaurantBefore11Hour(vote1)) {
            // получаем голос за ресторан за сегодня
            Vote vote = getByRestaurantIdUserIdAndLOcalDate(restaurantId, userId, LocalDateTime.now());

            if (vote == null) {
                vote = new Vote();
                vote.setUserId(userId);
                vote.setRestaurantId(restaurantId);
                vote.setDate_vote(vote1.getDate_vote());
            }
            vote.setVote(countVote);

            votesRepository.save(vote);

            int countInRestaurant = getCountVote(restaurantId);

            Restaurant restaurant = restaurantRepository.get(restaurantId);
            restaurant.setVote_count(countInRestaurant);

            restaurantRepository.save(restaurant);

        } else {
            throw new NotVoteException("Голосовать необходимо до 11 часов");
        }
    }

    public Vote create(Vote vote) throws NotFoundException, NotSaveOrUpdateException {
        return votesRepository.save(vote);
    }

  //  @CacheEvict(value = "users", allEntries = true)
    public Vote update(Vote vote) throws NotFoundException, NotSaveOrUpdateException {
        return votesRepository.save(vote);
    }
}
