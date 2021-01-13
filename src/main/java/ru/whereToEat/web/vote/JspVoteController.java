package ru.whereToEat.web.vote;

import org.springframework.stereotype.Controller;

@Controller
public class JspVoteController extends AbstractVoteController {

    /*
    *
    *     protected final Logger log = LoggerFactory.getLogger(getClass());

    private List<Vote> votes;


    private VoteRestController controller;

    private static String LIST_VOTES = "WEB-INF/jsp/votes.jsp";
    private static String LIST_INDEX = "index.html";
    private static String INSERT_OR_UPDATE = "WEB-INF/jsp/voteCreateOrUpdate.jsp";
    private static String UPDATE = "WEB-INF/jsp/updateVote.jsp";
    private static int restaurantId = 100003;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        controller = springContext.getBean(VoteRestController.class);
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


        String forward = "WEB-INF/jsp/votes.jsp";
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
            forward = "WEB-INF/jsp/votes.jsp";
            request.setAttribute("votes", controller.getAll());
        }

        log.debug("doGet()");
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }*/
}
