package ru.whereToEat.web.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/restaurants")
public class JspRestaurantController extends AbstractRestaurantController{

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("restaurants", super.getAll());
        return "restaurants";
    }

    /*
    *
    *     private RestaurantRestController controller;
    private VoteRestController voteRestController;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        controller = springContext.getBean(RestaurantRestController.class);
        voteRestController = springContext.getBean(VoteRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("restaurantId");

        // чему соответствует id
        // если "" то ресторан новый
        // если не "" то проверить есть ли в базе
        Restaurant restaurantIsNew = (id==null) ? new Restaurant() :controller.get(getRestaurantId(request));


        if (restaurantIsNew.isNew()) {
            Restaurant restaurant = new Restaurant();
            restaurant.setName(request.getParameter("name"));
            restaurant.setVote_count(0);
            controller.create(restaurant);
        } else {
            int restaurantId = getRestaurantId(request);
            Restaurant restaurant = controller.get(restaurantId);
            restaurant.setName(request.getParameter("name"));
            restaurant.setVote_count(getRestaurantCount(request));
            controller.update(restaurant);
        }

        response.sendRedirect("restaurants");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "vote":
                int restaurantId = getRestaurantId(request);
                int countVote = getRestaurantCount(request);
                int userId = SecurityUtil.authUserId();
                try {
                    Vote vote = new Vote(null, userId, LocalDateTime.now(), restaurantId, countVote);
                    voteRestController.voter(vote);
                    //voteRestController.voter(restaurantId, userId, countVote);
                } catch (NotFoundException | NotSaveOrUpdateException | NotVoteException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("restaurants");
                break;
            case "delete":
                int id = getRestaurantId(request);
                controller.delete(id);
                response.sendRedirect("restaurants");
                break;
            case "create":
            case "update":
                final Restaurant restaurant = "create".equals(action) ?
                        new Restaurant() :
                        controller.get(getRestaurantId(request));
                request.setAttribute("restaurant", restaurant);
                request.getRequestDispatcher("WEB-INF/jsp/restaurantForm.jsp").forward(request, response);
                break;
            case "filter":
                String nameRestaurant = request.getParameter("nameRestaurant");
                Collection<Restaurant> filteredRestaurants = controller.getFilteredByName(nameRestaurant);
                request.setAttribute("restaurants", filteredRestaurants);
                request.getRequestDispatcher("WEB-INF/jsp/restaurants.jsp").forward(request, response);
            case "all":
            default:
                Collection<Restaurant> restaurants = controller.getAll();
                request.setAttribute("restaurants", restaurants);
                request.getRequestDispatcher("WEB-INF/jsp/restaurants.jsp").forward(request, response);
                break;
        }

    }

    private int getRestaurantId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("restaurantId"));
        String paramIdTrim = paramId.trim();
        return Integer.parseInt(paramIdTrim);
    }

    private int getRestaurantCount(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("count"));
        return Integer.parseInt(paramId);
    }*/
}
