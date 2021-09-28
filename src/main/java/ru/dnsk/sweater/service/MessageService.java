package ru.dnsk.sweater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dnsk.sweater.model.Message;
import ru.dnsk.sweater.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> messageList(String searchTag) {
        List<Message> messageList;

        if (searchTag != null && !searchTag.isEmpty()) {
            messageList = messageRepository.findAllByTag(searchTag);
        } else {
            messageList = messageRepository.findAll();
        }

        return messageList;
    }
}
