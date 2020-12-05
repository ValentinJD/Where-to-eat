package ru.whereToEat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.whereToEat.Profiles;
import ru.whereToEat.model.Role;
import ru.whereToEat.model.User;
import ru.whereToEat.util.TimeUtil;
import ru.whereToEat.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

public class UserServlet extends HttpServlet {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    //private UserService userService;
    private AdminRestController controller;


    @Override
    public void init(ServletConfig config) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/spring-app.xml", "spring/spring-db.xml"}, false);
        context.getEnvironment().setActiveProfiles(Profiles.getActiveDbProfile(), "jpa");
        context.refresh();
        //ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        //userService = context.getBean(UserService.class);
        controller = context.getBean(AdminRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("userId");

        String login = request.getParameter("login");

        if (login != null && request.getParameter("login").equals("yes")) {
            SecurityUtil.setUserId(getId(request));
            response.sendRedirect("users");
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
                request.getRequestDispatcher("jsp/userForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                Collection<User> users = controller.getAll();
                request.setAttribute("users", users);
                request.getRequestDispatcher("jsp/users.jsp").forward(request, response);
                break;
        }




        /*request.setAttribute("users", users);
        request.getRequestDispatcher("jsp/users.jsp").forward(request, response);
        log.debug("1 debug redirect to users");*/

    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("userId"));
        return Integer.parseInt(paramId);
    }
}
