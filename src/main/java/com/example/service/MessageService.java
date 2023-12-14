package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Message message) {
        // 현재 Message 엔티티에 'sender' 필드가 없으므로, 이 부분은 제거합니다.
        // 필요한 경우 Message 엔티티를 수정하거나 다른 로직을 적용해야 합니다.

        return messageRepository.save(message);
    }

    public void readMessage(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid message Id: " + messageId));
        message.setIsRead(true);
        messageRepository.save(message);
    }

    public List<Message> getMessages(Long chatRoomId) {
        return messageRepository.findAllByChatRoomId(chatRoomId);
    }

}
