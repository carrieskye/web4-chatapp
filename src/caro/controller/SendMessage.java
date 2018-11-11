package caro.controller;

import caro.domain.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendMessage extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userId = getUserFromSession(request).getUserId();
        String friendId = request.getParameter("friend");
        String message = request.getParameter("message");
        long timeStamp = System.currentTimeMillis() / 1000L;

        Message newMessage = new Message(String.valueOf(timeStamp), userId, friendId, message, timeStamp);
        getMessageService().addMessage(newMessage);
    }

}
