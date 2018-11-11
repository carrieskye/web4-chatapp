package caro.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import caro.domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetStatus extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Person user = getUserFromSession(request);
        String quoteJSON = this.toJSON(getPersonService().getPerson(user.getUserId()));
        response.setContentType("application/json");
        response.getWriter().write(quoteJSON);
    }

    private String toJSON(Person person) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(person);
    }

}
