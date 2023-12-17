package com.example.controller;

import com.example.dto.ChatBotResponse;
import com.example.dto.QuestionRequest;
import com.example.service.ChatBotService;
import com.example.util.APIResponse;
import com.example.constant.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-bot")
public class ChatBotController {

    private final ChatBotService chatBotService;

    @PostMapping("/question")
    public ResponseEntity<?> sendQuestion(@RequestBody QuestionRequest questionRequest) {
        try {
            ChatBotResponse chatBotResponse = chatBotService.askQuestion(questionRequest);
            return APIResponse.success(chatBotResponse);
        } catch (Exception e) {
            return APIResponse.error("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
