package com.example.service;

import com.example.dto.MessageDto;
import com.example.entity.ChatRoom;
import com.example.entity.Message;
import com.example.repository.ChatRoomRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, ChatRoomRepository chatRoomRepository) {
        this.messageRepository = messageRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    public Message saveMessage(MessageDto messageDto) {
        ChatRoom chatRoom = chatRoomRepository.findById(messageDto.getChatRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid chatRoom Id"));

        Message message = Message.builder()
                .chatRoom(chatRoom)
                .content(messageDto.getContent())
                .senderId(messageDto.getSenderId())
                .isRead(false)
                .timestamp(new Date())
                .build();

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

    public Message saveMessageAsClinicMember(MessageDto messageDto) {
        // ChatRoom을 찾고, Message 객체를 생성하는 로직은 기존 saveMessage 메소드와 동일
        ChatRoom chatRoom = chatRoomRepository.findById(messageDto.getChatRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid chatRoom Id"));

        Message message = Message.builder()
                .chatRoom(chatRoom)
                .content(messageDto.getContent())
                .senderId(messageDto.getSenderId()) // 클리닉 멤버의 ID
                .isRead(false)
                .timestamp(new Date())
                .build();

        return messageRepository.save(message);
    }
}