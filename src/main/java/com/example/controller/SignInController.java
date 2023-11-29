package com.example.controller;

import com.example.dto.SignInRequestDto;
import com.example.dto.SignInResponseDto;
import com.example.repository.MemberRepository;
import com.example.service.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignInController {
    private final MemberRepository memberRepository;
    private final SignInService memberService;

    @PostMapping(value = "/login")
    public ResponseEntity<SignInResponseDto> signin(@RequestBody SignInRequestDto request) throws Exception {
        return new ResponseEntity<>(memberService.login(request), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Boolean> signup(@RequestBody SignInRequestDto request) throws Exception {
        return new ResponseEntity<>(memberService.register(request), HttpStatus.OK);
    }
    @GetMapping("/admin/get")
    public ResponseEntity<SignInResponseDto> getUserForAdmin(@RequestParam String email) throws Exception {
        return new ResponseEntity<>( memberService.getMember(email), HttpStatus.OK);
    }
}