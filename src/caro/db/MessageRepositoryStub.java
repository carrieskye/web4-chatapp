package caro.db;

import caro.domain.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageRepositoryStub implements MessageRepository {

    private Map<String, Message> messages = new HashMap<>();
    private static MessageRepositoryStub INSTANCE;

    private MessageRepositoryStub() {
        messages.put("1", new Message("1", "jan@ucll.be", "an@ucll.be", "Hi An!", System.currentTimeMillis() / 1000L - 127805));
        messages.put("2", new Message("2", "an@ucll.be", "jan@ucll.be", "Hello Jan", System.currentTimeMillis() / 1000L - 117805));
        messages.put("3", new Message("3", "jan@ucll.be", "jonas@ucll.be", "Hello Jonas, how are you?", System.currentTimeMillis() / 1000L - 12785));
        messages.put("4", new Message("4", "jonas@ucll.be", "jan@ucll.be", "I'm great! How are you?", System.currentTimeMillis() / 1000L - 8785));
        messages.put("5", new Message("5", "jan@ucll.be", "an@ucll.be", "It's been a while!", System.currentTimeMillis() / 1000L - 97804));
        messages.put("6", new Message("6", "jan@ucll.be", "an@ucll.be", "How have you been?", System.currentTimeMillis() / 1000L - 97803));
        messages.put("7", new Message("7", "an@ucll.be", "jan@ucll.be", "Okay, what about you?", System.currentTimeMillis() / 1000L - 4569));
        messages.put("8", new Message("8", "jan@ucll.be", "an@ucll.be", "I'm good, thanks", System.currentTimeMillis() / 1000L - 3475));
    }

    public static MessageRepositoryStub getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MessageRepositoryStub();
        }
        return INSTANCE;
    }

    public Message get(String messageId) {
        if (messageId == null) {
            throw new IllegalArgumentException("No id given");
        }
        return messages.get(messageId);
    }

    public List<Message> getAll() {
        return new ArrayList<>(messages.values());
    }

    public void add(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("No message given");
        }
        if (messages.containsKey(message.getMessageId())) {
            throw new IllegalArgumentException("Message already exists");
        }
        messages.put(message.getMessageId(), message);
    }

    public void update(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("No person given");
        }
        messages.put(message.getMessageId(), message);
    }

    public void delete(String messageId) {
        if (messageId == null) {
            throw new IllegalArgumentException("No id given");
        }
        messages.remove(messageId);
    }

}
