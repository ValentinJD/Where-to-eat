package ru.wheretoeat.web.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.wheretoeat.exceptions.NotFoundException;
import ru.wheretoeat.exceptions.NotSaveOrUpdateException;
import ru.wheretoeat.exceptions.NotVoteException;
import ru.wheretoeat.model.Restaurant;
import ru.wheretoeat.model.Vote;
import ru.wheretoeat.web.SecurityUtil;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Objects;

@ApiIgnore
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
    public String createOrUpdate(HttpServletRequest request, @Valid Restaurant restaurant) throws UnsupportedEncodingException {

        request.setCharacterEncoding("UTF-8");

        if (restaurant.isNew()) {
            restaurant.setVote_count(0);
            super.create(restaurant);
        } else {
            Restaurant restaurant1 = super.get(restaurant.id());
            restaurant1.setName(restaurant.getName());
            restaurant1.setVote_count(getRestaurantCount(request));
            super.update(restaurant1);
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
