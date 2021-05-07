package ru.wheretoeat.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.wheretoeat.exceptions.NotVoteException;
import ru.wheretoeat.model.Vote;
import ru.wheretoeat.service.RestaurantService;
import ru.wheretoeat.web.SecurityUtil;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/ui/vote")
public class VoteUIController extends AbstractVoteController {

    private final RestaurantService restaurantService;

    public VoteUIController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @PostMapping(value = "/{restId}/{count}")
    @ResponseStatus(HttpStatus.CREATED)
    public void vote(@PathVariable Integer restId, @PathVariable Integer count) throws NotVoteException {

        Vote vote1 = new Vote();
        vote1.setUserId(SecurityUtil.authUserId());
        vote1.setRestaurantId(restId);
        vote1.setDate_vote(LocalDateTime.now());
        vote1.setVote(count);
        super.voter(vote1);
    }

    @GetMapping(value = "/{restId}")
    public Integer getByRestaurantId(@PathVariable Integer restId) {
        return restaurantService.get(restId).getVote_count();
    }
}
