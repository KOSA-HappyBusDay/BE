//package com.example.controller;
//
//import com.example.service.StorageService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/storage")
//public class StorageController {
//    private final StorageService storageService;
//
//    // 생성자 주입
//    public StorageController(StorageService storageService) {
//        this.storageService = storageService;
//    }
//
//    @PostMapping("/upload/image")
//    // TODO: 권한 체크 및 멤버 정보 확인 등 필요한 어노테이션 추가
//    public ResponseEntity<?> uploadImage(
//            @RequestHeader("Authorization") String authorization,
//            @RequestParam("image") MultipartFile file,
//            @RequestParam("forehead") Integer forehead,
//            @RequestParam("leftCheek") Integer leftCheek,
//            @RequestParam("rightCheek") Integer rightCheek,
//            @RequestParam("chin") Integer chin
//    ) throws IOException {
//        String uploadImage = storageService.uploadImage(authorization, file, forehead, leftCheek, rightCheek, chin);
//        return ResponseEntity.ok(uploadImage);
//    }
//
//}
//
//    // 나머지 코드는 그대로 유지됩니다.
//
//
//    // 영상 업로드 부분은 주석 처리합니다.
////    @PostMapping("/upload/video")
////    @PreAuthorize("hasRole('USER')")
////    public ResponseEntity<?> uploadVideo(@RequestHeader("Authorization") String authorization, @RequestParam("video") MultipartFile videoFile, @RequestParam("videoDuration") String videoDuration) throws IOException {
////        String uploadVideo = storageService.uploadVideo(videoFile, authorization, videoDuration);
////        return ResponseEntity.status(HttpStatus.OK).body(uploadVideo);
////    }
