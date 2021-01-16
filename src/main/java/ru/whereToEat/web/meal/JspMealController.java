package ru.whereToEat.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController extends AbstractMealController{

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getMealId(request));
        return "redirect:/restaurants";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("meal", super.get(getMealId(request)));
        return "mealForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("meal", new Meal("", 0.0f));
        return "mealForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");


        Restaurant restaurant = restaurantService.get(getRestaurantId(request));

        Meal meal = new Meal(request.getParameter("description"),
                Float.parseFloat(request.getParameter("price")));
        meal.setRestaurant(restaurant);

        if (request.getParameter("mealId").isEmpty()) {

            super.create(meal);
        } else {
            meal.setId(getMealId(request));
            super.update(meal);
        }
        return "redirect:/restaurants";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    private int getMealId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("mealId").trim());
        return Integer.parseInt(paramId);
    }

    private int getRestaurantId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("restaurantId"));
        return Integer.parseInt(paramId);
    }


    /*
    *
    private RestaurantService restaurantService;

    private MealRestController mealRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        restaurantService = springContext.getBean(RestaurantService.class);
        mealRestController = springContext.getBean(MealRestController.class);
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
            mealRestController.create(meal);
        } else {
            int mealId = getMealId(request);
            Meal meal = mealRestController.get(mealId);
            meal.setDescription(request.getParameter("description"));
            meal.setPrice(Float.parseFloat(request.getParameter("price")));

            mealRestController.update(meal);
        }

        response.sendRedirect("restaurants");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete" -> {
                int id = getMealId(request);
                mealRestController.delete(id);
                response.sendRedirect("meals");
            }
            case "create", "update" -> {
                final Meal meal = "create".equals(action) ?
                        new Meal("", 0f) :
                        mealRestController.get(getMealId(request));

                request.setAttribute("restaurantId", getRestaurantId(request));

                request.setAttribute("meal", meal);
                request.getRequestDispatcher("WEB-INF/jsp/mealForm.jsp").forward(request, response);
            }
            default -> {
                Collection<Restaurant> restaurants = restaurantService.getAll();
                request.setAttribute("restaurants", restaurants);
                request.getRequestDispatcher("WEB-INF/jsp/restaurants.jsp").forward(request, response);
            }
        }
    }

    private int getMealId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("mealId").trim());
        return Integer.parseInt(paramId);
    }

    private int getRestaurantId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("restaurantId"));
        return Integer.parseInt(paramId);
    }


    * */
}
