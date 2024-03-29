package ru.wheretoeat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.wheretoeat.exceptions.NotFoundException;
import ru.wheretoeat.exceptions.NotSaveOrUpdateException;
import ru.wheretoeat.exceptions.NotVoteException;
import ru.wheretoeat.model.Restaurant;
import ru.wheretoeat.model.Vote;
import ru.wheretoeat.repository.RestaurantRepository;
import ru.wheretoeat.repository.VotesRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.wheretoeat.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    VotesRepository votesRepository;

    RestaurantRepository restaurantRepository;

    public VoteService(VotesRepository votesRepository, RestaurantRepository restaurantRepository) {
        this.votesRepository = votesRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Vote> getallbyrestarauntid(int restaurantId) {

        return votesRepository.getAll(restaurantId).stream()
                .filter(vote -> vote.getDate_vote().toLocalDate().isEqual(LocalDate.now()))
                .sorted(Comparator.comparing(Vote::getId))
                .collect(Collectors.toList());
    }

    public Vote getVoteByRestaurantIdUserIdAndLOcalDate(int restaurantId, int userId, LocalDateTime ldt) {
        return votesRepository.getByRestaurantIdUserIdAndLocalDate(restaurantId, userId, ldt);
    }

    public List<Vote> getAll() {
        return votesRepository.getAllForTest();
    }


    public void delete(int voteId) {
        checkNotFoundWithId(votesRepository.delete(voteId), voteId);
    }

    public Vote get(int voteId) {
        return checkNotFoundWithId(votesRepository.get(voteId), voteId);
    }

    private boolean isVoteUserInRestaurantBefore11Hour(Vote vote) {
        Objects.requireNonNull(vote);
        return vote.getDate_vote().getHour() < 11;
    }

    public int getCountVote(int restaurantId) {
        List<Vote> voteList = getallbyrestarauntid(restaurantId);

        int count = 0;

        for (Vote vote : voteList) {
            if (vote.getVoteCount() > 0) {
                count++;
            }
            if (vote.getVoteCount() < 0) {
                count--;
            }
        }
        return count;
    }

    public void voter(Vote vote1) throws NotFoundException, NotSaveOrUpdateException, NotVoteException {
        Assert.notNull(vote1, "vote must not be null");

        if (isVoteUserInRestaurantBefore11Hour(vote1)) {
            saveOrUpdateVote(vote1);
        } else {
            throw new NotVoteException("Голосовать необходимо до 11 часов");
        }
    }

    private void saveOrUpdateVote(Vote vote1) {
        int restaurantId = vote1.getRestaurantId();
        int userId = vote1.getUserId();
        int countVote = vote1.getVoteCount();

        // получаем голос за ресторан за сегодня
        Vote voteOnToday = getVoteByRestaurantIdUserIdAndLOcalDate(restaurantId, userId, LocalDateTime.now());

        if (voteOnToday == null) {
            getNewVote(userId, restaurantId, vote1.getDate_vote());
        }

        voteOnToday.setVoteCount(countVote);

        votesRepository.save(voteOnToday);

        updateVotesForRestaurant(restaurantId);
    }

    private Vote getNewVote(int userId, int restaurantId, LocalDateTime date) {

        Vote oldVote = new Vote();
        oldVote.setUserId(userId);
        oldVote.setRestaurantId(restaurantId);
        oldVote.setDate_vote(date);

        return oldVote;
    }

    private void updateVotesForRestaurant(int restaurantId) {

        int countInRestaurant = getCountVote(restaurantId);

        Restaurant restaurant = restaurantRepository.get(restaurantId);
        restaurant.setVote_count(countInRestaurant);

        restaurantRepository.save(restaurant);
    }


    public Vote create(Vote vote) throws NotFoundException, NotSaveOrUpdateException {
        return votesRepository.save(vote);
    }

    public Vote update(Vote vote) throws NotFoundException, NotSaveOrUpdateException {
        return votesRepository.save(vote);
    }
}
