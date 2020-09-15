package ru.whereToEat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.repository.jdbc.JDBCRestaurantRepository;
import ru.whereToEat.service.RestaurantService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RestaurantServlet extends HttpServlet {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private List<Restaurant> restaurants;

    @Override
    public void init(ServletConfig config) {
        RestaurantService service = new RestaurantService(new JDBCRestaurantRepository());
        restaurants = service.getall();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("restaurants", restaurants);
        request.getRequestDispatcher("jsp/restaurants.jsp").forward(request, response);
        log.debug("redirect to restaurants");

    }
}
