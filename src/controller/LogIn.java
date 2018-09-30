package controller;

import domain.Person;
import domain.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogIn extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination = "chat.jsp";
        List<String> errors = new ArrayList<>();

        String email = request.getParameter("email");
        if (email == null || email.isEmpty()) {
            errors.add("No email given");
        }

        String password = request.getParameter("password");
        if (password == null || password.isEmpty()) {
            errors.add("No password given");
        }

        if (errors.size() == 0) {
            PersonService personService = super.getPersonService();
            Person person = personService.getAuthenticatedUser(email, password);
            if (person != null) {
                person.setStatus("online");
                createSession(person, request, response);
            } else {
                errors.add("No valid email/password");
            }
        }

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
        }

        return destination;
    }

    private void createSession(Person person, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("user", person);

        PersonService personService = super.getPersonService();
        session.setAttribute("friends", person.getFriends()
                .stream()
                .map(personService::getPerson)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

}
