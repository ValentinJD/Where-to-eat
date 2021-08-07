package ru.wheretoeat.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static ru.wheretoeat.model.AbstractBaseEntity.START_SEQ;

@Entity
@Access(AccessType.FIELD)
@Table(name = "history_votes",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "restaurant_id", "date_vote"}, name = "history_votes_user_restaurant_idx")})
@NamedQueries({
        @NamedQuery(name = Vote.ALL_SORTED_BY_RESTAURANT_ID, query = "SELECT v FROM Vote v WHERE v.restaurantId=?1 ORDER BY v.id"),
        @NamedQuery(name = Vote.ALL_SORTED, query = "SELECT v FROM Vote v ORDER BY v.id"),
        @NamedQuery(name = Vote.ALL_SORTED_BY_RESTAURANT_ID_AND_USER_ID,
                query = "SELECT v FROM Vote v WHERE v.restaurantId=?1 AND v.userId =?2 ORDER BY v.id"),
        @NamedQuery(name = Vote.ALL_SORTED_BY_RESTAURANT_ID_AND_USER_ID_AND_DATEVOTE,
                query = "SELECT v FROM Vote v WHERE v.restaurantId=?1 AND v.userId =?2 AND v.date_vote=?3 ORDER BY v.id")
})
public class Vote {

    public static final String ALL_SORTED_BY_RESTAURANT_ID = "Vote.getAllSortedByRestaurantId";
    public static final String ALL_SORTED = "Vote.getAllSorted";
    public static final String ALL_SORTED_BY_RESTAURANT_ID_AND_USER_ID = "Vote.getAllSortedByRestaurantIdAndUserId";
    public static final String ALL_SORTED_BY_RESTAURANT_ID_AND_USER_ID_AND_DATEVOTE =
            "Vote.getAllSortedByRestaurantIdAndUserIdAndDateVote";

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "date_vote", nullable = false)
    @NotNull
    private LocalDateTime date_vote;

    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId;

    @Column(name = "vote", nullable = false)
    private int voteCount;

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public Vote() {
    }

    public Vote(Integer id, int userId, LocalDateTime date_vote, int restaurantId, int vote) {
        this.id = id;
        this.userId = userId;
        this.date_vote = date_vote;
        this.restaurantId = restaurantId;
        this.voteCount = vote;
    }

    public boolean isNew() {
        return id == null;
    }

    // doesn't work for hibernate lazy proxy
    public int id() {
        Assert.notNull(id, "Entity must has id");
        return id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate_vote() {
        return date_vote;
    }

    public void setDate_vote(LocalDateTime date_vote) {
        this.date_vote = date_vote;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", userId=" + userId +
                ", date_vote=" + date_vote +
                ", restaurantId=" + restaurantId +
                ", vote=" + voteCount +
                '}';
    }

    public boolean getIsNew() {
        return id == null;
    }
}
