package ru.whereToEat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.jdbc.JDBCMealRepository;
import ru.whereToEat.repository.jdbc.JDBCVotesRepository;
import ru.whereToEat.service.MealService;
import ru.whereToEat.service.VoteService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class VoteServlet extends HttpServlet {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private List<Vote> votes;

    @Override
    public void init(ServletConfig config) {
        VoteService service = new VoteService(new JDBCVotesRepository());
        try {
            votes = service.getallbyrestarauntid(100002);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("votes", votes);
        request.getRequestDispatcher("jsp/votes.jsp").forward(request, response);
        log.debug("redirect to votes");

    }

}
