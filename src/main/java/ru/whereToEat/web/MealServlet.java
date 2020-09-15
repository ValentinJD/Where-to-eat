package ru.whereToEat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.User;
import ru.whereToEat.repository.jdbc.JDBCMealRepository;
import ru.whereToEat.repository.jdbc.JDBCUserRepository;
import ru.whereToEat.service.MealService;
import ru.whereToEat.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MealServlet extends HttpServlet {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private List<Meal> meals;

    @Override
    public void init(ServletConfig config) {
        MealService service = new MealService(new JDBCMealRepository());
        meals = service.getAll(100002);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("meals", meals);
        request.getRequestDispatcher("jsp/meals.jsp").forward(request, response);
        log.debug("redirect to meals");

    }


}
