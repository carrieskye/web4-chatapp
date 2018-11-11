package caro.controller;

import caro.domain.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GetMessages extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String friendId = request.getParameter("friend");
        String userId = getUserFromSession(request).getUserId();
        long lastMessageDateTime = Long.parseLong(request.getParameter("lastTimeStamp"));

        ArrayList<Message> messages = (ArrayList<Message>) getMessageService().getMessages()
                .stream()
                .filter(message -> (message.getReceiverId().equals(friendId) && message.getSenderId().equals(userId))
                        || (message.getReceiverId().equals(userId) && message.getSenderId().equals(friendId)))
                .filter(message -> message.getTimeStamp() > lastMessageDateTime)
                .sorted((o1, o2) -> (int) (o1.getTimeStamp() - o2.getTimeStamp()))
                .collect(Collectors.toList());

        String quoteJSON = this.toJSON(messages);
        response.setContentType("application/json");
        response.getWriter().write(quoteJSON);
    }

    private String toJSON(ArrayList<Message> messages) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(messages);
    }

}
