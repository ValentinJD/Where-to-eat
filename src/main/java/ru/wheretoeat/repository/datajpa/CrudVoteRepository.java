package ru.wheretoeat.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.wheretoeat.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    List<Vote> findAllByRestaurantId(int restaurantId);

    List<Vote> findAllByRestaurantIdAndUserId(int restaurantId, int userId);

    @Query("SELECT v FROM Vote v WHERE v.id=:restaurantId AND " +
            "v.userId=:userId and v.date_vote=:ldt")
    Vote getByRestaurantIdAndUserIdAndDate_vote(
            @Param("restaurantId") int restaurantId,
            @Param("userId") int userId,
            @Param("ldt") LocalDateTime ldt);
}
