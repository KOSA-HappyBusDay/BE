package com.example.controller;

import com.example.dto.SignInRequestDto;
import com.example.dto.SignInResponseDto;
import com.example.service.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class SignInController {
    private final SignInService signInService;

    @PostMapping("/login")
    public ResponseEntity<SignInResponseDto> signin(@RequestBody SignInRequestDto request) throws Exception {
        return new ResponseEntity<>(signInService.login(request), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> signup(@RequestBody SignInRequestDto request) throws Exception {
        return new ResponseEntity<>(signInService.register(request), HttpStatus.OK);
    }

    @PostMapping("/admin/get")
    public ResponseEntity<SignInResponseDto> getUserForAdmin(@RequestBody String email) throws Exception {
        return new ResponseEntity<>(signInService.getMember(email), HttpStatus.OK);
    }
}
