//package com.example.controller;
//
//import com.example.entity.ChatRoom;
//import com.example.entity.Message;
//import com.example.service.MessageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/chat")
//public class WebSocketController {
//    private final MessageService messageService;
//
//    @Autowired
//    public WebSocketController(MessageService messageService) {
//        this.messageService = messageService;
//    }
//
//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public Message send(Message message) {
//        return messageService.saveMessage(message);
//    }
//
//    @MessageMapping("/chat/read/{messageId}")
//    @SendTo("/topic/chat/read/{messageId}")
//    public void readMessage(@DestinationVariable Long messageId) {
//        messageService.readMessage(messageId);
//    }
//
//
//}