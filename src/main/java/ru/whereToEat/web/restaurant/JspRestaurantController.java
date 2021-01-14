package ru.whereToEat.web.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.exceptions.NotVoteException;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.model.Vote;
import ru.whereToEat.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Objects;

@Controller
@RequestMapping(value = "/restaurants")
public class JspRestaurantController extends AbstractRestaurantController {

    @GetMapping("/filter")
    public String filter(HttpServletRequest request, Model model) {
        String filter = request.getParameter("nameRestaurant");
        model.addAttribute("restaurants", super.getFilteredByName(filter));
        return "restaurants";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        int id = getRestaurantId(request);
        super.delete(id);
        return "redirect:/restaurants";
    }

    @GetMapping("/create")
    public String create(Model model) {
        final Restaurant restaurant = new Restaurant();
        model.addAttribute("restaurant", restaurant);
        return "restaurantForm";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        final Restaurant restaurant = super.get(getRestaurantId(request));
        model.addAttribute("restaurant", restaurant);
        return "restaurantForm";
    }

    @GetMapping("/vote")
    public String voter(HttpServletRequest request, Model model) {

        int restaurantId = getRestaurantId(request);
        int countVote = getRestaurantCount(request);
        int userId = SecurityUtil.authUserId();
        try {
            Vote vote = new Vote(null, userId, LocalDateTime.now(), restaurantId, countVote);
            super.voter(vote);
        } catch (NotFoundException | NotSaveOrUpdateException | NotVoteException e) {
            e.printStackTrace();
        }
        return "redirect:/restaurants";
    }


    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("restaurants", super.getAll());
        return "restaurants";
    }

    @PostMapping
    public String createOrUpdate(HttpServletRequest request, Model model) throws UnsupportedEncodingException {

        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("restaurantId");

        Restaurant restaurantIsNew = (id == null)|| id.isEmpty() ? new Restaurant() : super.get(getRestaurantId(request));

        if (restaurantIsNew.isNew()) {
            Restaurant restaurant = new Restaurant();
            restaurant.setName(request.getParameter("name"));
            restaurant.setVote_count(0);
            super.create(restaurant);
        } else {
            int restaurantId = getRestaurantId(request);
            Restaurant restaurant = super.get(restaurantId);
            restaurant.setName(request.getParameter("name"));
            restaurant.setVote_count(getRestaurantCount(request));
            super.update(restaurant);
        }

        return "redirect:/restaurants";
    }

    private int getRestaurantId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("restaurantId"));
        String paramIdTrim = paramId.trim();
        return Integer.parseInt(paramIdTrim);
    }

    private int getRestaurantCount(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("count"));
        return Integer.parseInt(paramId);
    }
}
