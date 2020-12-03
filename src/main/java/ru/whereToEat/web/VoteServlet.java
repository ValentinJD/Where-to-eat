package ru.whereToEat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.whereToEat.Profiles;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.model.Vote;
import ru.whereToEat.web.vote.VoteRestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class VoteServlet extends HttpServlet {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private List<Vote> votes;

    //private VoteService voteService;
    private VoteRestController controller;

    private static String LIST_VOTES = "jsp/votes.jsp";
    private static String LIST_INDEX = "index.html";
    private static String INSERT_OR_UPDATE = "jsp/voteCreateOrUpdate.jsp";
    private static String UPDATE = "jsp/updateVote.jsp";
    private static int restaurantId = 100003;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/spring-app.xml", "spring/spring-db.xml"}, false);
        context.getEnvironment().setActiveProfiles(Profiles.getActiveDbProfile());
        context.refresh();
        //ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");

        controller = context.getBean(VoteRestController.class);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        String forward = "";

        forward = LIST_VOTES;

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
            controller.create(vote);
            request.setAttribute("vote", vote);
        } catch (NotSaveOrUpdateException | NotFoundException e) {
            e.printStackTrace();
        }

        List<Vote> voteList = controller.getAll();

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

        if (action.equalsIgnoreCase("listVotes")) {
            forward = LIST_VOTES;

            List<Vote> voteList = controller.getAll();
            request.setAttribute("votes", voteList);

        } else if (action.equalsIgnoreCase("delete")) {
            int voteId = Integer.parseInt(request.getParameter("voteId"));

            controller.delete(voteId);
            forward = LIST_VOTES;
            List<Vote> voteList = controller.getallbyrestarauntid(restaurantId);
            request.setAttribute("votes", voteList);

        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_UPDATE;
            int voteId = Integer.parseInt(request.getParameter("voteId"));

            Vote vote = controller.get(voteId);
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
