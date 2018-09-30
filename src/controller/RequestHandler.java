package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.PersonService;
import domain.Person;
import domain.Role;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class RequestHandler {

    private PersonService personService;

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response);

    void setModel(PersonService personService) {
        this.personService = personService;
    }

    PersonService getPersonService() {
        return personService;
    }

    protected boolean isFromUserWithRole(HttpServletRequest request, Role role) {
        Person person = (Person) request.getSession().getAttribute("user");
        return person != null && person.getRole().equals(role);
    }

    protected void createSession(Person person, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("user", person);

        PersonService personService = getPersonService();
        session.setAttribute("friends", person.getFriends()
                .stream()
                .map(personService::getPerson)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

}
