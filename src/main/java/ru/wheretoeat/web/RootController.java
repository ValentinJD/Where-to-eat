package ru.wheretoeat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.wheretoeat.service.UserService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class RootController {
    @Autowired
    private UserService service;

    @GetMapping("/")
    public String root() {
        return "redirect:restaurants";
    }

    //    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public String getUsers() {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }



/*
*     protected final Logger log = LoggerFactory.getLogger(getClass());


    private AdminRestController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        controller = springContext.getBean(AdminRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("userId");

        String login = request.getParameter("login");

        if (login != null && request.getParameter("login").equals("yes")) {
            SecurityUtil.setUserId(getId(request));
            response.sendRedirect("restaurants");
            return;
        }


        if (id.equals("")) {
            User user = new User();

            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setEnabled(Boolean.valueOf(request.getParameter("enabled")));
            user.setRegistered(LocalDateTime.parse(TimeUtil.toDateFormatString(request.getParameter("registered"))));
            user.setRole(Role.valueOf(request.getParameter("role")));

            controller.create(user);
        } else {
            int userId = getId(request);
            User user = controller.get(userId);
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setEnabled(Boolean.getBoolean(request.getParameter("enabled")));
            user.setRegistered(LocalDateTime.parse(TimeUtil.toDateFormatString(request.getParameter("registered"))));
            user.setRole(Role.valueOf(request.getParameter("role")));

            controller.update(user, SecurityUtil.authUserId());
        }
        log.info(id.equals("") ? "Create {}" : "Update {}");
        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                controller.delete(id);
                response.sendRedirect("users");
                break;
            case "create":
            case "update":
                final User user = "create".equals(action) ?
                        new User() :
                        controller.get(getId(request));
                request.setAttribute("user", user);
                request.getRequestDispatcher("WEB-INF/jsp/userForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                Collection<User> users = controller.getAll();
                request.setAttribute("users", users);
                request.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(request, response);
                //src/main/webapp/WEB-INF/jsp/users.jsp
                break;
        }






}

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("userId"));
        return Integer.parseInt(paramId);
    }
* */

}
