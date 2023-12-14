package com.example.controller;

import com.example.dto.ClinicMemberSignInRequestDto;
import com.example.dto.ClinicMemberSignInResponseDto;
import com.example.service.ClinicMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinic-members")
public class ClinicMemberController {
    @Autowired
    private ClinicMemberService clinicMemberService;

    @PostMapping("/login")
    public ResponseEntity<ClinicMemberSignInResponseDto> login(@RequestBody ClinicMemberSignInRequestDto request) throws Exception {
        ClinicMemberSignInResponseDto responseDto = clinicMemberService.login(request);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<ClinicMemberSignInResponseDto> signup(@RequestBody ClinicMemberSignInRequestDto request) throws Exception {
        ClinicMemberSignInResponseDto responseDto = clinicMemberService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClinicMemberSignInResponseDto>> getClinics() {
        List<ClinicMemberSignInResponseDto> clinics = clinicMemberService.getClinics();
        return ResponseEntity.ok(clinics);
    }
}
