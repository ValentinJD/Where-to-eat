package ru.whereToEat.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.whereToEat.exceptions.NotEnoughRightsException;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.Vote;
import ru.whereToEat.service.VoteService;
import ru.whereToEat.web.SecurityUtil;

import java.time.LocalDate;
import java.util.List;

@Controller
public class VoteRestController {
    final VoteService voteService;

    public VoteRestController(VoteService voteService) {
        this.voteService = voteService;
    }

    public Vote get(int restaurantId) {
        int userId = SecurityUtil.authUserId();
        return voteService.getByRestaurantIdUserIdAndLOcalDate(restaurantId, userId, LocalDate.now());
    }

    public void delete(int voteId) {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            voteService.delete(voteId);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }

    }

    public List<Vote> getAll() {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            return voteService.getAll();
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }
    }

    public Vote create(Vote vote) throws NotFoundException, NotSaveOrUpdateException {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            return voteService.create(vote);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }
    }

    public void update(Vote vote) throws NotFoundException, NotSaveOrUpdateException {
        int id = SecurityUtil.authUserId();
        if (SecurityUtil.isAdmin(id)) {
            voteService.update(vote);
        } else {
            throw new NotEnoughRightsException("Только для администраторов");
        }
    }

    public List<Vote> getallbyrestarauntid(int restaurantId) {
        return voteService.getallbyrestarauntid(restaurantId);
    }
}
