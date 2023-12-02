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
@RequestMapping("/image")
public class StorageController {
    final private StorageService storageService;


    // 업로드
    @PostMapping("/upload")@PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> uploadImage(@RequestHeader("Authorization") String Authorization, @RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = storageService.uploadImage(file,Authorization);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }




    // 영상 업로드
    @PostMapping("/upload/video")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> uploadVideo(@RequestHeader("Authorization") String Authorization, @RequestParam("video") MultipartFile videoFile, @RequestParam("videoDuration") String videoDuration) throws IOException {
        // 여기에 영상 업로드를 처리하는 코드 추가
        String uploadVideo = storageService.uploadVideo(videoFile, Authorization, videoDuration);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadVideo);
    }

//    // 영상 업로드
//    @PostMapping("/video")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> uploadVideo(@RequestHeader("Authorization") String Authorization, @RequestParam("video") MultipartFile videoFile, @RequestParam("videoDuration") String videoDuration) throws IOException {
//        // 여기에 영상 업로드를 처리하는 코드 추가
//        String uploadVideo = storageService.uploadVideo(videoFile, Authorization, videoDuration);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(uploadVideo);
//    }


    // 다운로드
    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName) {
        byte[] downloadImage = storageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }

    // 추가 GET 엔드포인트를 사용한 다운로드
    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadImageAlternate(@PathVariable("fileName") String fileName) {
        byte[] downloadImage = storageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }

    @GetMapping("/download/video/{fileName}")
    public ResponseEntity<?> downloadVideo(@PathVariable("fileName") String fileName) {
        // 여기에 영상 다운로드를 처리하는 코드 추가
        byte[] downloadVideo = storageService.downloadVideo(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("video/mp4")) // 예시로 mp4 형식을 사용했습니다. 실제로 사용하는 형식에 맞게 변경해야 합니다.
                .body(downloadVideo);
    }
}



