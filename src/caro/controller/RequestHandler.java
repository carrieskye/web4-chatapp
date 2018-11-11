package caro.controller;

import caro.domain.MessageService;
import caro.domain.Person;
import caro.domain.PersonService;
import caro.domain.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class RequestHandler {

    private PersonService personService;
    private MessageService messageService;

    public abstract void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    void setModel(PersonService personService, MessageService messageService) {
        this.personService = personService;
        this.messageService = messageService;
    }

    PersonService getPersonService() {
        return personService;
    }

    MessageService getMessageService() {
        return messageService;
    }

    protected boolean isFromUserWithRole(HttpServletRequest request, Role role) {
        Person person = (Person) request.getSession().getAttribute("user");
        return person != null && person.getRole().equals(role);
    }

    Person getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = ((Person) session.getAttribute("user")).getUserId();
        return getPersonService().getPerson(userId);
    }

}
