package com.example.controller;

import com.example.dto.MessageDto;
import com.example.dto.MessageResponse;
import com.example.dto.TranslateRequest;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.service.MessageService;
import com.example.repository.ChatRoomRepository;
import com.example.service.PapagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;
    private final MessageRepository messageRepository;
    private final PapagoService papagoService;  // Add this line

    @Autowired
    public MessageController(MessageService messageService, MessageRepository messageRepository, PapagoService papagoService) {  // Modify this line
        this.messageService = messageService;
        this.messageRepository = messageRepository;
        this.papagoService = papagoService;  // Add this line
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDto messageDto) {
        messageService.saveMessage(messageDto);
        return ResponseEntity.ok(new MessageResponse("Message sent successfully"));
    }


//    @MessageMapping("/message.send")
//    @SendTo("/topic/public")
//    public Message sendMessage(@Payload MessageDto messageDto) {
//        Message savedMessage = messageService.saveMessage(messageDto);
//        return savedMessage;
//    }

    @GetMapping("/{chatRoomId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long chatRoomId) {
        List<Message> messages = messageService.getMessages(chatRoomId);
        return ResponseEntity.ok(messages);
    }

    @PutMapping("/{id}/read")
    public void readMessage(@PathVariable Long id) {
        messageService.readMessage(id);
    }

    @PostMapping("/translate")
    public ResponseEntity<String> translateMessage(@RequestBody TranslateRequest request) {
        Message message = messageRepository.findById(request.getMessageId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid message Id"));
        String translatedText = papagoService.translate(message.getContent());
        return ResponseEntity.ok(translatedText);
    }

//    @MessageMapping("/message.translate")
//    @SendTo("/topic/public")
//    public String translateMessage(@Payload TranslateRequest request) {
//        Message message = messageRepository.findById(request.getMessageId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid message Id"));
//        String translatedText = papagoService.translate(message.getContent());
//        return translatedText;
//    }



    @PostMapping("/clinicMember")
    public ResponseEntity<?> sendMessageAsClinicMember(@RequestBody MessageDto messageDto) {
        messageService.saveMessageAsClinicMember(messageDto);
        return ResponseEntity.ok(new MessageResponse("Clinic member message sent successfully"));
    }


}

