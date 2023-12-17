package com.example.controller;

import com.example.dto.ChatRoomCreateRequest;
import com.example.entity.ChatRoom;
import com.example.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatroom")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping("/create")
    public ChatRoom createOrGetChatRoom(@RequestBody ChatRoomCreateRequest request) {
        return chatRoomService.createOrGetChatRoom(request.getMemberId(), request.getClinicMemberId(), request.getName());
    }

    @GetMapping("/{memberId}/{clinicMemberId}")
    public ChatRoom getChatRoom(@PathVariable Long memberId, @PathVariable Long clinicMemberId) {
        return chatRoomService.getChatRoom(memberId, clinicMemberId);
    }

    @GetMapping("/list")
    public List<ChatRoom> getChatRooms() {
        return chatRoomService.getChatRooms();
    }
}
