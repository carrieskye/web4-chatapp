package caro.controller;

import caro.domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GetUsers extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String users = "[ ";

        users += getPersonService().getPersons().stream().map(person ->
                "{ " + " \"firstName\": \"" + person.getFirstName()
                        + "\", \"lastName\": \"" + person.getLastName()
                        + "\", \"email\": \"" + person.getUserId()
                        + "\", \"gender\": \"" + person.getGender()
                        + "\", \"age\": \"" + person.getAge()
                        + "\" }, ").collect(Collectors.joining());
        users += "]";
        users = users.replace("}, ]", "} ]");

        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getWriter().write(users);
    }

}
