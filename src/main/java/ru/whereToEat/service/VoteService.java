package ru.whereToEat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.exceptions.NotVoteException;
import ru.whereToEat.model.CountVote;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.CountVoteRepository;
import ru.whereToEat.repository.RestaurantRepository;
import ru.whereToEat.repository.VotesRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
                .filter(vote -> vote.getDate_vote().toLocalDate().isEqual(
                        LocalDate.now())).
                        collect(Collectors.toList());
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

    private boolean isVoteUserInRestaurantBefore11Hour() {
        return LocalDateTime.now().getHour() < 24;
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

    public Vote vote(Vote vote) throws NotSaveOrUpdateException, NotVoteException, NotFoundException {
        log.info("vote {}", vote);

        if (isVoteUserInRestaurantBefore11Hour()) {

            return votesRepository.save(vote);
        }
        throw new NotVoteException("голосование проходит только до 11 часов");
    }


    public void voter(int restaurantId, int userId, int countVote) throws NotFoundException, NotSaveOrUpdateException, NotVoteException {

        if (isVoteUserInRestaurantBefore11Hour()) {

            // получаем голос за ресторан за сегодня


            Vote vote = getByRestaurantIdUserIdAndLOcalDate(restaurantId, userId, LocalDate.now());

            if (vote == null) {
                vote = new Vote();
                vote.setUserId(userId);
                vote.setRestaurantId(restaurantId);
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
