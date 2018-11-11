package caro.domain;

import caro.db.MessageRepository;
import caro.db.MessageRepositoryStub;

import java.util.List;

public class MessageService {
    private MessageRepository messageRepository = MessageRepositoryStub.getInstance();

    public MessageService() {
    }

    public Message getMessage(String messageId) {
        return getMessageRepository().get(messageId);
    }

    public List<Message> getMessages() {
        return getMessageRepository().getAll();
    }

    public void addMessage(Message message) {
        getMessageRepository().add(message);
    }

    public void updateMessages(Message message) {
        getMessageRepository().update(message);
    }

    public void deleteMessage(String id) {
        getMessageRepository().delete(id);
    }

    private MessageRepository getMessageRepository() {
        return messageRepository;
    }
}
