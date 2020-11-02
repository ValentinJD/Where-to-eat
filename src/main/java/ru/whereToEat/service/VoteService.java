package ru.whereToEat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
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

    public Vote getByRestaurantIdUserIdAndLOcalDate(int restaurantId, int userId, LocalDate ldt) {
        return votesRepository.getByRestaurantIdUserIdAndLocalDate(restaurantId, userId, ldt);
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

    private boolean isVoteUserInRestaurantBefore11Hour(Vote vote) {
        Objects.requireNonNull(vote);
        return vote.getDate_vote().getHour() < 11;
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

    private Vote save(Vote vote) throws NotSaveOrUpdateException, NotVoteException, NotFoundException {
        log.info("vote {}", vote);

        if (isVoteUserInRestaurantBefore11Hour(vote)) {

            return votesRepository.save(vote);
        }
        throw new NotVoteException("голосование проходит только до 11 часов");
    }


    public void voter(Vote vote1) throws NotFoundException, NotSaveOrUpdateException, NotVoteException {
        int restaurantId = vote1.getRestaurantId();
        int userId = vote1.getUserId();
        int countVote = vote1.getVote();

        if (isVoteUserInRestaurantBefore11Hour(vote1)) {

            // получаем голос за ресторан за сегодня


            Vote vote = getByRestaurantIdUserIdAndLOcalDate(restaurantId, userId, LocalDate.now());

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

    public Vote update(Vote vote) throws NotFoundException, NotSaveOrUpdateException {
        return votesRepository.save(vote);
    }
}
