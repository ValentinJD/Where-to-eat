package ru.whereToEat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.exceptions.NotVoteException;
import ru.whereToEat.model.Vote;
import ru.whereToEat.service.VoteService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        voteService = context.getBean(VoteService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        String forward = "";

        forward = LIST_VOTES;


        final LocalTime startTime = LocalTime.of(0, 0);
        final LocalTime endTime = LocalTime.of(23, 59, 59);

        Vote vote = new Vote();
        String voteId = request.getParameter("voteId");
        if (voteId != null) {
            vote.setId(Integer.parseInt(voteId));
        }
        String strUserId = request.getParameter("userId");
        if (strUserId != null) {
            vote.setUserId(Integer.parseInt(strUserId));
        }
        vote.setRestaurantId(Integer.parseInt(request.getParameter("restaurantId")));
        vote.setDate_vote(LocalDateTime.now());
        vote.setVote(Integer.parseInt(request.getParameter("vote")));
        try {
            voteService.vote(vote);
            request.setAttribute("vote", vote);
        } catch (NotSaveOrUpdateException e) {
            e.printStackTrace();
        } catch (NotVoteException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }


        List<Vote> voteList = null;
        voteList = voteService.getAll();

        request.setAttribute("votes", voteList);

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String forward = "";
        String action = "";

        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }

        final LocalTime startTime = LocalTime.of(0, 0);
        final LocalTime endTime = LocalTime.of(23, 59, 59);

        if (action.equalsIgnoreCase("listVotes")) {
            forward = LIST_VOTES;

            List<Vote> voteList = voteService.getAll();
            request.setAttribute("votes", voteList);

        } else if (action.equalsIgnoreCase("delete")) {
            int voteId = Integer.parseInt(request.getParameter("voteId"));

            voteService.delete(voteId);
            forward = LIST_VOTES;
            List<Vote> voteList = voteService.getallbyrestarauntid(restaurantId);
            request.setAttribute("votes", voteList);

        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_UPDATE;
            int voteId = Integer.parseInt(request.getParameter("voteId"));

            Vote vote = voteService.get(voteId);
            request.setAttribute("vote", vote);

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
