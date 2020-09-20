package ru.whereToEat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.service.MealService;
import ru.whereToEat.service.RestaurantService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private MealService mealService;
    private RestaurantService restaurantService;

    @Override
    public void init(ServletConfig config) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        mealService = context.getBean(MealService.class);
        restaurantService = context.getBean(RestaurantService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("mealId");

        Restaurant restaurant = restaurantService.get(getRestaurantId(request));

        if (id == null || id.equals("")) {
            Meal meal = new Meal(request.getParameter("description"),
                    Float.parseFloat(request.getParameter("price")));
            meal.setRestaurant(restaurant);
            mealService.save(meal);
        } else {
            int mealId = getMealId(request);
            Meal meal = mealService.get(mealId);
            meal.setDescription(request.getParameter("description"));
            meal.setPrice(Float.parseFloat(request.getParameter("price")));

            mealService.update(meal);
        }
        log.info(id == null ? "Create {}" : "Update {}");
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getMealId(request);
                log.info("Delete {}", id);
                mealService.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal("", 0f) :
                        mealService.get(getMealId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("jsp/mealForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                Collection<Meal> meals = mealService.getAll();
                request.setAttribute("meals", meals);
                request.getRequestDispatcher("jsp/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getMealId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("mealId"));
        return Integer.parseInt(paramId);
    }

    private int getRestaurantId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("restaurantId"));
        return Integer.parseInt(paramId);
    }




}
