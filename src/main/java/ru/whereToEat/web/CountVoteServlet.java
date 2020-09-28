package ru.whereToEat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.exceptions.NotVoteException;
import ru.whereToEat.model.CountVote;
import ru.whereToEat.model.Vote;
import ru.whereToEat.service.CountVoteService;
import ru.whereToEat.service.VoteService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class CountVoteServlet extends HttpServlet {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private List<Vote> votes;

    private CountVoteService countVoteService;

    private static String LIST_COUNT_VOTES = "jsp/countVotes.jsp";
    private static String LIST_INDEX = "index.html";
    private static String INSERT_OR_UPDATE = "jsp/countVoteCreateOrUpdate.jsp";
    private static String UPDATE = "jsp/updateCountVote.jsp";
    private static int restaurantId = 100002;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        countVoteService = context.getBean(CountVoteService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        String forward = "";

        forward = LIST_COUNT_VOTES;


        final LocalTime startTime = LocalTime.of(0, 0);
        final LocalTime endTime = LocalTime.of(23, 59, 59);

        CountVote countVote = new CountVote();
        String countVoteId = request.getParameter("countVoteId");
        if (countVoteId != null) {
            countVote.setId(Integer.parseInt(countVoteId));
        }
        String restaurantId = request.getParameter("restaurantId");
        if (restaurantId != null) {
            countVote.setRestaurantId(Integer.parseInt(restaurantId));
        }
        countVote.setRestaurantId(Integer.parseInt(request.getParameter("restaurantId")));
        //countVote.setDate(LocalDate.now());
        countVote.setCount(Integer.parseInt(request.getParameter("countVote")));
        try {
            countVoteService.save(countVote);
            request.setAttribute("countVote", countVote);
        } catch (NotSaveOrUpdateException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }


        List<CountVote> countVotes = null;
        countVotes = countVoteService.getAll();

        request.setAttribute("countVotes", countVotes);

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

        if (action.equalsIgnoreCase("listCountVotes")) {
            forward = LIST_COUNT_VOTES;

            List<CountVote> countVotes = countVoteService.getAll();
            request.setAttribute("countVotes", countVotes);

        } else if (action.equalsIgnoreCase("delete")) {
            int countVoteId = Integer.parseInt(request.getParameter("countVoteId"));

            countVoteService.delete(countVoteId);
            forward = LIST_COUNT_VOTES;
            List<CountVote> voteList = countVoteService.getallbyrestarauntid(restaurantId);
            request.setAttribute("countVotes", voteList);

        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_UPDATE;
            int countVoteId = Integer.parseInt(request.getParameter("countVoteId"));

            CountVote countVote = countVoteService.get(countVoteId);
            request.setAttribute("countVote", countVote);

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
