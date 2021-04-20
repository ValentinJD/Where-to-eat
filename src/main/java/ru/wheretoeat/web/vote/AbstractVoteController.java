package ru.wheretoeat.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.wheretoeat.exceptions.NotEnoughRightsException;
import ru.wheretoeat.exceptions.NotFoundException;
import ru.wheretoeat.exceptions.NotSaveOrUpdateException;
import ru.wheretoeat.exceptions.NotVoteException;
import ru.wheretoeat.model.Vote;
import ru.wheretoeat.service.VoteService;
import ru.wheretoeat.web.SecurityUtil;

import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractVoteController {

    @Autowired
    private VoteService voteService;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public Vote get(int restaurantId) {
        int userId = SecurityUtil.authUserId();
        Vote vote = voteService.getByRestaurantIdUserIdAndLOcalDate(restaurantId, userId, LocalDateTime.now());
        log.info("get() {}", vote);
        return vote;
    }

    public void delete(int voteId) {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            voteService.delete(voteId);
            log.info("delete() voteId {}", voteId);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }

    }

    public List<Vote> getAll() {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            log.info("getAll() ");
            return voteService.getAll();

        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }
    }

    public Vote create(Vote vote) throws NotFoundException, NotSaveOrUpdateException {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            log.info("create() vote {}", vote);
            return voteService.create(vote);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }
    }

    public void update(Vote vote) throws NotFoundException, NotSaveOrUpdateException {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            log.info("update() vote {}", vote);
            voteService.update(vote);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }
    }

    public List<Vote> getallbyrestarauntid(int restaurantId) {
        log.info("getallbyrestarauntid() restaurantId {}", restaurantId);
        return voteService.getallbyrestarauntid(restaurantId);
    }



    public void voter(Vote vote) throws NotSaveOrUpdateException, NotVoteException, NotFoundException {
        voteService.voter(vote);
        //voteService.voter(vote.getRestaurantId(), vote.getUserId(),vote.getVote());
    }

}
