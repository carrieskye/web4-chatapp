package caro.db;

import caro.domain.Message;

import java.util.List;

public interface MessageRepository {

    Message get(String messageId);

    List<Message> getAll();

    void add(Message message);

    void update(Message message);

    void delete(String messageId);

}
