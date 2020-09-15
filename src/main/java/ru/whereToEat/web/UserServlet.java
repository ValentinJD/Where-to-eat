package ru.whereToEat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.whereToEat.model.Role;
import ru.whereToEat.model.User;
import ru.whereToEat.repository.jdbc.JDBCUserRepository;
import ru.whereToEat.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserServlet extends HttpServlet {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private List<User> users;

    @Override
    public void init(ServletConfig config) {
        UserService service = new UserService(new JDBCUserRepository());
        /*service.create(new User("Юзер", "user@mail.ru", "password", Role.USER));
        service.create(new User("Админ", "admin@mail.ru", "password", Role.ADMIN));*/
        users = service.getAll();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("users", users);
        request.getRequestDispatcher("jsp/users.jsp").forward(request, response);
        log.debug("1 debug redirect to users");

    }
}
