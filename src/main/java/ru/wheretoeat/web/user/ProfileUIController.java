package ru.wheretoeat.web.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import ru.wheretoeat.AuthorizedUser;
import ru.wheretoeat.to.UserTo;
import ru.wheretoeat.web.SecurityUtil;


import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class ProfileUIController extends AbstractUserController {
    public static final String EXCEPTION_DUPLICATE_EMAIL = "exception.user.duplicateEmail";

    @GetMapping
    public String profile(ModelMap model, @AuthenticationPrincipal AuthorizedUser authUser) {
        model.addAttribute("userTo", authUser.getUserTo());
        return "profile";
    }

    @PostMapping
    public String updateProfile(@Valid UserTo userTo, BindingResult result, SessionStatus status, @AuthenticationPrincipal AuthorizedUser authUser) {
        if (result.hasErrors()) {
            return "profile";
        }
        try {
            super.update(userTo, authUser.getId());
            authUser.update(userTo);
            status.setComplete();
            return "redirect:restaurants";
        } catch (DataIntegrityViolationException ex) {
            result.rejectValue("email", EXCEPTION_DUPLICATE_EMAIL);
            return "profile";
        }
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("userTo", new UserTo());
        model.addAttribute("register", true);
        return "profile";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "profile";
        }

        try {
            super.create(userTo);
            status.setComplete();
            return "redirect:/login?message=app.registered&username=" + userTo.getEmail();
        } catch (DataIntegrityViolationException ex) {
            result.rejectValue("email", EXCEPTION_DUPLICATE_EMAIL);
            model.addAttribute("register", true);
            return "profile";
        }
    }
}
