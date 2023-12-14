package com.example.controller;

import com.example.dto.MessageDto;
import com.example.dto.MessageResponse;
import com.example.entity.Message;
import com.example.service.MessageService;
import com.example.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;
    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public MessageController(MessageService messageService, ChatRoomRepository chatRoomRepository) {
        this.messageService = messageService;
        this.chatRoomRepository = chatRoomRepository;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDto messageDto) {
        Message message = new Message();
        message.setChatRoom(chatRoomRepository.findById(messageDto.getChatRoomId()).orElseThrow(
                () -> new IllegalArgumentException("ChatRoom Not Found with id: " + messageDto.getChatRoomId())));
        message.setContent(messageDto.getContent());
        message.setTimestamp(new Date());
        // sender 관련 로직은 제거되었습니다.

        messageService.saveMessage(message);

        return ResponseEntity.ok(new MessageResponse("Message sent successfully"));
    }

    @GetMapping("/{chatRoomId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long chatRoomId) {
        List<Message> messages = messageService.getMessages(chatRoomId);
        return ResponseEntity.ok(messages);
    }

    @PutMapping("/{id}/read")
    public void readMessage(@PathVariable Long id) {
        messageService.readMessage(id);
    }
}


