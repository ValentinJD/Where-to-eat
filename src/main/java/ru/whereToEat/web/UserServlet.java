package ru.whereToEat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/users.jsp").forward(request, response);
        log.debug("1 debug redirect to users");
//        log.info(" 2 info redirect to users");
//        log.warn(" 3 warn redirect to users");
//        log.trace(" 4-5 trace redirect to users");
//        log.error(" 4 error redirect to users");
//        response.sendRedirect("jsp/users.jsp");
    }
}
