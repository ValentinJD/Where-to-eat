package ru.wheretoeat.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.wheretoeat.exceptions.NotVoteException;
import ru.wheretoeat.model.Vote;
import ru.wheretoeat.service.RestaurantService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = VoteRestController.REST_URL_VOTE, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractVoteController {
    public static final String REST_URL_VOTE = "/rest/vote";

    private final RestaurantService restaurantService;

    public VoteRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void vote(@RequestBody @Valid Vote vote) throws NotVoteException {
        super.voter(vote);
    }

}
