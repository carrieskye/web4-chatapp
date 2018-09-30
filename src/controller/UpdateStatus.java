package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateStatus extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination = "chat.jsp";

        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("user");
        person.setStatus(request.getParameter("status"));
        session.setAttribute("user", person);

        return destination;
    }
}
