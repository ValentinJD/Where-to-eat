package ru.whereToEat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.exceptions.NotVoteException;
import ru.whereToEat.model.Vote;
import ru.whereToEat.service.VoteService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class VoteServlet extends HttpServlet {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private List<Vote> votes;

    private VoteService voteService;

    private static String LIST_VOTES = "jsp/votes.jsp";
    private static String LIST_INDEX = "index.html";
    private static String INSERT_OR_UPDATE = "jsp/voteCreateOrUpdate.jsp";
    private static String UPDATE = "jsp/updateVote.jsp";
    private static int restaurantId = 100003;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        String forward = "";

        forward = LIST_VOTES;

        String action = "";
        action = request.getParameter("action");

        final LocalTime startTime = LocalTime.of(0, 0);
        final LocalTime endTime = LocalTime.of(23, 59, 59);

        Vote vote = new Vote();
        vote.setId(Integer.parseInt(request.getParameter("voteId")));
        vote.setUserId(Integer.parseInt(request.getParameter("userId")));
        vote.setRestaurantId(Integer.parseInt(request.getParameter("restaurantId")));
        vote.setDate_vote(LocalDateTime.parse(request.getParameter("dateVote")));
        vote.setVote(Integer.parseInt(request.getParameter("vote")));
        try {
            voteService.vote(vote);
        } catch (NotSaveOrUpdateException e) {
            e.printStackTrace();
        } catch (NotVoteException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }


        List<Vote> voteList = null;
        try {
            voteList = voteService.getallbyrestarauntid(restaurantId);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("votes", voteList);

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        voteService = new VoteService();

        String forward = "";
        String action = "";

        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }

        final LocalTime startTime = LocalTime.of(0, 0);
        final LocalTime endTime = LocalTime.of(23, 59, 59);

        if (action.equalsIgnoreCase("listVotes")) {
            forward = LIST_VOTES;
            try {
                List<Vote> voteList = voteService.getallbyrestarauntid(restaurantId);
                request.setAttribute("votes", voteList);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("delete")) {
            int voteId = Integer.parseInt(request.getParameter("voteId"));
            try {
                voteService.delete(voteId);
                forward = LIST_VOTES;
                List<Vote> voteList = voteService.getallbyrestarauntid(restaurantId);
                request.setAttribute("votes", voteList);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_UPDATE;
            int voteId = Integer.parseInt(request.getParameter("voteId"));

            try {
                Vote vote = voteService.get(voteId);
                request.setAttribute("vote", vote);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("create")) {
            forward = INSERT_OR_UPDATE;

        } else {
            forward = LIST_INDEX;
        }

        log.debug("doGet()");
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

}
