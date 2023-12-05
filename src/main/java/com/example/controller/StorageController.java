package com.example.controller;

import com.example.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storage")
public class StorageController {
    private final StorageService storageService;

    @PostMapping("/upload/image")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> uploadImage(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("image") MultipartFile file,
            @RequestParam("forehead") Integer forehead,
            @RequestParam("leftCheek") Integer leftCheek,
            @RequestParam("rightCheek") Integer rightCheek,
            @RequestParam("chin") Integer chin
    ) throws IOException {
        String uploadImage = storageService.uploadImage(file, authorization, forehead, leftCheek, rightCheek, chin);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    // 영상 업로드 부분은 주석 처리합니다.
//    @PostMapping("/upload/video")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> uploadVideo(@RequestHeader("Authorization") String authorization, @RequestParam("video") MultipartFile videoFile, @RequestParam("videoDuration") String videoDuration) throws IOException {
//        String uploadVideo = storageService.uploadVideo(videoFile, authorization, videoDuration);
//        return ResponseEntity.status(HttpStatus.OK).body(uploadVideo);
//    }

    @GetMapping("/download/image/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName) {
        byte[] downloadImage = storageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG) // 이미지의 형식에 맞게 설정
                .body(downloadImage);
    }
}
