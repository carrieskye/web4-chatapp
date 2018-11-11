package caro.server;

import com.google.gson.Gson;
import caro.db.BlogRepository;
import caro.db.BlogRepositoryStub;
import caro.domain.Blog;
import caro.domain.Comment;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/blog")
public class BlogServer {

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    private BlogRepository blogRepository = BlogRepositoryStub.getInstance();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.getId() + " has opened a connection");
        sendMessageToAll("User " + session.getId() + " has connected");
        try {
            String result = new Gson().toJson(new JsonMessage("Connection Established"));
            session.getBasicRemote().sendText(result);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        JsonMessage jsonMessage = new Gson().fromJson(message, JsonMessage.class);
        try {
            if (jsonMessage.getAction().equals("getPreviousComments")) {
                String result = new Gson().toJson(new JsonMessage("previousComments", blogRepository.getAll()));
                session.getBasicRemote().sendText(result);
            } else if (jsonMessage.getAction().equals("newComment")) {
                System.out.println(jsonMessage.getData().toString());
                Comment newComment = new Gson().fromJson(jsonMessage.getData().toString(), Comment.class);
                Blog blog = blogRepository.get(newComment.getBlogId());
                blog.addComment(newComment);
                blogRepository.update(blog);
                String result = new Gson().toJson(new JsonMessage("newComment", newComment));
                System.out.println("Message from " + session.getId() + ": " + result);
                sendMessageToAll(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Chat " + session.getId() + " has ended");
        sessions.remove(session);
    }

    private void sendMessageToAll(String message) {
        for (Session s : sessions) {
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class JsonMessage {

        private String action;
        private Object data;

        private JsonMessage(String action) {
            this.action = action;
        }

        private JsonMessage(String action, Object data) {
            this.action = action;
            this.data = data;
        }

        String getAction() {
            return action;
        }

        Object getData() {
            return data;
        }
    }

}
