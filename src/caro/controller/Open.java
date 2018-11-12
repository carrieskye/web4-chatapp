package caro.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Open extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher view;
        switch (request.getParameter("page")) {
            case "Chat":
                if (request.getSession().getAttribute("user") == null) {
                    view = request.getRequestDispatcher("Controller");
                } else {
                    view = request.getRequestDispatcher("chat.jsp");
                }
                break;
            case "Blog":
                view = request.getRequestDispatcher("blog.jsp");
                break;
            case "Register":
                view = request.getRequestDispatcher("register.jsp");
                break;
            default:
                view = request.getRequestDispatcher("Controller");
        }
        view.forward(request, response);
    }

}
