package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class GetFriends extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Person> friends = new ArrayList<>();
        ArrayList<String> friendIds = getUserFromSession(request).getFriends();
        friendIds.forEach(friendId -> friends.add(getPersonService().getPerson(friendId)));

        String quoteJSON = this.toJSON(friends);
        response.setContentType("application/json");
        response.getWriter().write(quoteJSON);
    }

    private String toJSON(ArrayList<Person> friends) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(friends);
    }

}
