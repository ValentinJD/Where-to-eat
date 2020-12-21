package ru.whereToEat.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.VotesRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaVotesRepository implements VotesRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote) throws NotFoundException, NotSaveOrUpdateException {

        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Override
    @Transactional
    public boolean delete(int voteId) {
        Vote vote = em.getReference(Vote.class, voteId);
        em.remove(vote);
        return !em.contains(vote);
    }

    @Override
    public Vote get(int voteId) {
        return em.find(Vote.class, voteId);
    }

    @Override
    public List<Vote> getAll(int restaurantId) {
        return em.createNamedQuery(Vote.ALL_SORTED_BY_RESTAURANT_ID, Vote.class)
                .setParameter(1, restaurantId)
                .getResultList();
    }

    @Override
    public List<Vote> getAllForTest() {
        return em.createNamedQuery(Vote.ALL_SORTED, Vote.class)
                .getResultList();
    }

    @Override
    public List<Vote> getByRestaurantAndUserId(int restaurantId, int userId) {
        return em.createNamedQuery(Vote.ALL_SORTED_BY_RESTAURANT_ID_AND_USER_ID, Vote.class)
                .setParameter(1, restaurantId)
                .setParameter(2, userId)
                .getResultList();
    }



    @Override
    public Vote getByRestaurantIdUserIdAndLocalDate(int restaurantId, int userId, LocalDateTime ldt) {

        return em.createNamedQuery(Vote.ALL_SORTED_BY_RESTAURANT_ID_AND_USER_ID_AND_DATEVOTE, Vote.class)
                .setParameter(1, restaurantId)
                .setParameter(2, userId)
                .setParameter(3, ldt)
                .getSingleResult();
    }
}
