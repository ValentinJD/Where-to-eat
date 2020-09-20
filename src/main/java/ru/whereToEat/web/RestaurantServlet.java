package ru.whereToEat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.service.RestaurantService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

public class RestaurantServlet extends HttpServlet {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private RestaurantService restaurantService;

    @Override
    public void init(ServletConfig config) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        restaurantService = context.getBean(RestaurantService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("restaurantId");

        if (id.equals("")) {
            Restaurant restaurant = new Restaurant();
            restaurant.setName(request.getParameter("name"));
            restaurant.setVote_count(getRestaurantCount(request));
            restaurantService.save(restaurant);
        } else {
            int restaurantId = getRestaurantId(request);
            Restaurant restaurant = restaurantService.get(restaurantId);
            restaurant.setName(request.getParameter("name"));
            restaurant.setVote_count(getRestaurantCount(request));
            restaurantService.update(restaurant);
        }
        log.info(id.equals("") ? "Create {}" : "Update {}");
        response.sendRedirect("restaurants");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getRestaurantId(request);
                log.info("Delete {}", id);
                restaurantService.delete(id);
                response.sendRedirect("restaurants");
                break;
            case "create":
            case "update":
                final Restaurant restaurant = "create".equals(action) ?
                        new Restaurant() :
                        restaurantService.get(getRestaurantId(request));
                request.setAttribute("restaurant", restaurant);
                request.getRequestDispatcher("jsp/restaurantForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                Collection<Restaurant> restaurants = restaurantService.getAll();
                request.setAttribute("restaurants", restaurants);
                request.getRequestDispatcher("jsp/restaurants.jsp").forward(request, response);
                break;
        }

    }

    private int getRestaurantId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("restaurantId"));
        return Integer.parseInt(paramId);
    }

    private int getRestaurantCount(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("count"));
        return Integer.parseInt(paramId);
    }
}
