package com.example.controller;

import com.example.service.FacePictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/face-picture")
public class FacePictureController {
    private final FacePictureService facePictureService;


    @PostMapping("/upload")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> uploadFacePicture(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("forehead") MultipartFile forehead,
            @RequestParam("leftCheek") MultipartFile leftCheek,
            @RequestParam("rightCheek") MultipartFile rightCheek,
            @RequestParam("chin") MultipartFile chin
    ) throws IOException {
        // TODO: 인증 및 멤버 정보 확인 등 필요한 로직 수행

        // FacePicture 서비스 메소드 호출에 전달되는 파라미터 변경
        String memberId = facePictureService.jwtTokenToPayload(authorization);
        byte[] foreheadBytes = forehead.getBytes();
        byte[] leftCheekBytes = leftCheek.getBytes();
        byte[] rightCheekBytes = rightCheek.getBytes();
        byte[] chinBytes = chin.getBytes();

        // FacePicture 저장
        facePictureService.saveFacePicture(foreheadBytes, leftCheekBytes, rightCheekBytes, chinBytes, memberId);

        return ResponseEntity.status(HttpStatus.OK).body("Face picture uploaded successfully.");
    }
}
