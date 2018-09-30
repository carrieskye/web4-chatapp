package controller;

import domain.Person;
import domain.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddFriend extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination = "chat.jsp";

        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("user");
        String friendId = request.getParameter("friend");
        person.addFriend(friendId);

        PersonService personService = super.getPersonService();
        Person friend = personService.getPerson(friendId);
        friend.addFriend(person.getUserId());

        personService.updatePersons(person);
        personService.updatePersons(friend);

        super.createSession(person, request, response);

        return destination;
    }

}
