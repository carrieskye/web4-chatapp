package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import domain.PersonService;
import domain.Person;
import domain.Role;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class RequestHandler {

    private PersonService personService;

    public abstract void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

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

    Person getUserFromSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        String userId = ((Person) session.getAttribute("user")).getUserId();
        return getPersonService().getPerson(userId);
    }

}
