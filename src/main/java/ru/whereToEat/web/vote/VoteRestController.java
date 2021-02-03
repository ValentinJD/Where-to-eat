package ru.whereToEat.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.whereToEat.exceptions.NotVoteException;
import ru.whereToEat.model.Vote;
import ru.whereToEat.web.SecurityUtil;
import ru.whereToEat.web.restaurant.RestaurantRestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = VoteRestController.REST_URL_VOTE, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractVoteController {
    public static final String  REST_URL_VOTE = "/rest/vote";

    @PutMapping(value = "/{restaurantId}/{count}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@PathVariable int restaurantId, @PathVariable int count) throws NotVoteException {
        Vote vote = new Vote(null, SecurityUtil.authUserId(), LocalDateTime.now(), restaurantId, count);
        super.voter(vote);
    }
}
