package ru.whereToEat.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.whereToEat.exceptions.NotEnoughRightsException;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.exceptions.NotVoteException;
import ru.whereToEat.model.Vote;
import ru.whereToEat.service.VoteService;
import ru.whereToEat.web.SecurityUtil;

import java.time.LocalDate;
import java.util.List;

@Controller
public class VoteRestController {
    final VoteService voteService;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public VoteRestController(VoteService voteService) {
        this.voteService = voteService;
    }

    public Vote get(int restaurantId) {
        int userId = SecurityUtil.authUserId();
        Vote vote = voteService.getByRestaurantIdUserIdAndLOcalDate(restaurantId, userId, LocalDate.now());
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

    public void voter(int restaurantId, int userId, int countVote) throws NotSaveOrUpdateException, NotVoteException, NotFoundException {
        voteService.voter(restaurantId, userId,countVote);
    }
}
