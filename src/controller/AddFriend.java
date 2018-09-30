package controller;

import domain.Person;
import domain.PersonService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddFriend extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();

        Person person = getUserFromSession(request);
        String friendId = request.getParameter("friend");

        PersonService personService = super.getPersonService();
        if (personService.getPerson(friendId) == null) {
            errors.add("This person does not exist.");
        }

        if (errors.size() == 0) {
            try {
                person.addFriend(friendId);
            } catch (IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
            Person friend = personService.getPerson(friendId);
            friend.addFriend(person.getUserId());

            personService.updatePersons(person);
            personService.updatePersons(friend);
        }

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            RequestDispatcher view = request.getRequestDispatcher("chat.jsp");
            view.forward(request, response);
        }
    }

}
