package ru.wheretoeat.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.wheretoeat.exceptions.NotVoteException;
import ru.wheretoeat.model.Vote;

@RestController
@RequestMapping(value = VoteRestController.REST_URL_VOTE, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractVoteController {
    public static final String REST_URL_VOTE = "/rest/vote";

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void vote(@RequestBody Vote vote) throws NotVoteException {
        super.voter(vote);
    }
}
