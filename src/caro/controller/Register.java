package caro.controller;

import caro.domain.Person;
import caro.domain.PersonService;
import caro.domain.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static caro.domain.Role.*;

public class Register extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();

        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            int age = Integer.parseInt(request.getParameter("age"));
            String gender = request.getParameter("gender");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Person person = new Person(email, password, firstName, lastName, gender, age, LID, new ArrayList<>());
            getPersonService().addPerson(person);

            RequestDispatcher view = request.getRequestDispatcher("Controller?action=LogIn");
            view.forward(request, response);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            RequestDispatcher view = request.getRequestDispatcher("register.jsp");
            view.forward(request, response);
        }
    }

}
