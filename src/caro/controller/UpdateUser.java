package caro.controller;

import caro.domain.Person;
import caro.server.BlogServer;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import java.util.stream.Collectors;

public class UpdateUser extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String message = request.getReader().lines().collect(Collectors.joining());
            Properties jsonMessage = new Gson().fromJson(message, Properties.class);

            Person person = getPersonService().getPerson(jsonMessage.getProperty("email"));
            person.setFirstName(jsonMessage.getProperty("firstName"));
            person.setLastName(jsonMessage.getProperty("lastName"));
            person.setGender(jsonMessage.getProperty("gender"));
            person.setAge(Integer.parseInt(jsonMessage.getProperty("age")));
            getPersonService().updatePersons(person);

            RequestDispatcher view = request.getRequestDispatcher("Controller?action=GetUsers");
            view.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}
