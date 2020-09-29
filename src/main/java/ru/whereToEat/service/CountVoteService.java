package ru.whereToEat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.CountVote;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.CountVoteRepository;
import ru.whereToEat.repository.RestaurantRepository;
import ru.whereToEat.repository.VotesRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountVoteService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    CountVoteRepository countVoteRepository;

    RestaurantRepository restaurantRepository;

    public CountVoteService(CountVoteRepository countVoteRepository, RestaurantRepository restaurantRepository) {
        this.countVoteRepository = countVoteRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public void vote(CountVote countVote) {
    }

    public List<CountVote> getAll() {
        return countVoteRepository.getAll();
    }

    public void delete(int countVoteId) {
        countVoteRepository.delete(countVoteId);
    }

    public List<CountVote> getallbyrestarauntid(int restaurantId) {
        return countVoteRepository.getAll(restaurantId).stream()
                .filter(countVote -> countVote.getDate().isEqual(
                        LocalDate.now())).
                        collect(Collectors.toList());
    }

    public CountVote get(int restaurantId) {
        return countVoteRepository.get(restaurantId);
    }

    public void save(CountVote countVote) throws NotFoundException, NotSaveOrUpdateException {
        countVoteRepository.save(countVote);
    }
}
