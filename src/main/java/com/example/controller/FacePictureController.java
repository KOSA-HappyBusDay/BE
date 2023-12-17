package com.example.controller;

import com.example.dto.FacePictureResponse;
import com.example.entity.FacePicture;
import com.example.service.FacePictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/face-picture")
public class FacePictureController {
    private final FacePictureService facePictureService;


    @PostMapping("/upload")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> uploadFacePicture(
            @RequestHeader("Authorization") String authorization,
            @RequestPart(value = "forehead") MultipartFile forehead,
            @RequestPart(value = "leftCheek") MultipartFile leftCheek,
            @RequestPart(value = "rightCheek") MultipartFile rightCheek,
            @RequestPart(value = "chin") MultipartFile chin,
            @RequestPart(value = "AImessage") String AImessage
    ) throws IOException {
        // TODO: 인증 및 멤버 정보 확인 등 필요한 로직 수행

        // FacePicture 서비스 메소드 호출에 전달되는 파라미터 변경
        String memberId = facePictureService.jwtTokenToPayload(authorization);
        byte[] foreheadBytes = forehead.getBytes();
        byte[] leftCheekBytes = leftCheek.getBytes();
        byte[] rightCheekBytes = rightCheek.getBytes();
        byte[] chinBytes = chin.getBytes();

        // FacePicture 저장
        facePictureService.saveFacePicture(foreheadBytes, leftCheekBytes, rightCheekBytes, chinBytes, memberId, AImessage);

        return ResponseEntity.status(HttpStatus.OK).body("Face picture uploaded successfully.");
    }

    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<FacePictureResponse>> getFacePictureListByMemberId(@PathVariable Long memberId) {
        List<FacePictureResponse> facePictures = facePictureService.getFacePicturesByMemberId(memberId);
        return ResponseEntity.ok(facePictures);
    }

    @GetMapping("/detail/{facePictureId}")
    public ResponseEntity<FacePictureResponse> getFacePictureDetail(@PathVariable Long facePictureId) {
        FacePictureResponse facePicture = facePictureService.getFacePictureById(facePictureId);
//        System.out.println("Controller Response: " + facePicture);
        return ResponseEntity.ok(facePicture);
    }

    @GetMapping("/image/{type}/{id}")
    public void getImage(@PathVariable String type, @PathVariable Long id, HttpServletResponse response) throws IOException {
        byte[] imageBytes = facePictureService.getImageBytes(type, id);

        if (imageBytes == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("image/jpeg"); // Adjust the MIME type as needed
        response.getOutputStream().write(imageBytes);
    }

}