package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateStatus extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = getUserFromSession(request);
        person.setStatus(request.getParameter("status"));
        getPersonService().updatePersons(person);
    }

}
