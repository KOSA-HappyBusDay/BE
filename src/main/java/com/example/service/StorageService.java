package com.example.service;

import com.example.entity.ImageData;
import com.example.entity.MediaData;
import com.example.entity.Member;
import com.example.repository.MediaRepository;
import com.example.repository.MemberRepository;
import com.example.repository.StorageRepository;
import com.example.util.ImageUtils;
import com.example.util.MediaUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageRepository storageRepository;
    private final MediaRepository mediaRepository;
    private final MemberRepository memberRepository;

    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    public String uploadImage(MultipartFile file,String Authorization) throws IOException {
        Optional<Member> memberInformation = memberRepository.findByEmail(jwtTokenToPayload(Authorization));

        Member memberId = memberInformation.get();
        System.out.println("member : "+ memberId.getId());
        Member memberToId = new Member(memberId.getId());
        System.out.println(memberToId);
        ImageData imageData = storageRepository.save(
                ImageData.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .imageData(ImageUtils.compressImage(file.getBytes()))
                        .member(memberId)
                        .build());

        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }

        return null;
    }

    public String uploadVideo(MultipartFile file) throws IOException {

        MediaData mediaData = mediaRepository.save(
                MediaData.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .mediaData(MediaUtils.compressMedia(file.getBytes())) // 명시적인 제네릭 타입 지정
                        .build());
        if (mediaData != null) {
            return "동영상 파일이 성공적으로 업로드되었습니다: " + file.getOriginalFilename();
        }

        return null;
    }

    // 이미지 파일로 압축하기
    public byte[] downloadImage(String fileName) {
        ImageData imageData = storageRepository.findByName(fileName)
                .orElseThrow(RuntimeException::new);



        return ImageUtils.decompressImage(imageData.getImageData());
    }

    // 영상 파일로 압축 해제하기
//    public byte[] downloadVideo(String fileName) throws IOException {
//        MediaData mediaData = mediaRepository.findByName(fileName)
//                .orElseThrow(RuntimeException::new);
//
//        log.info("동영상 데이터 다운로드: {}", mediaData);
//
//        return MediaUtils.decompressMedia(mediaData.getMediaData());
//    }
    public byte[] downloadVideo(String fileName) {
        try {
            MediaData mediaData = mediaRepository.findByName(fileName)
                    .orElseThrow(RuntimeException::new);


            return MediaUtils.decompressMedia(mediaData.getMediaData());
        } catch (IOException e) {
            throw new RuntimeException("동영상 다운로드 중 오류 발생", e);
        }
    }

    public String jwtTokenToPayload(String Authorization){
        String jwtToken = Authorization.replace("Bearer", ""); // 실제 토큰 값으로 대체

        // JWT Token 복호화를 위한 키 (비밀 키)
        String secretKey = jwtSecretKey; // 실제 키 값으로 대체

        // 토큰 파서 생성
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes()) // 비밀 키 설정
                .build();

        // 토큰 파싱
        Claims claims = parser.parseClaimsJws(jwtToken).getBody();

        // 추출할 정보
        String userId = claims.getSubject();
        // 기타 원하는 정보들을 claims에서 추출

        // 추출한 정보 사용
        return userId;
    }

}
