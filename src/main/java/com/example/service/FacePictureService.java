package com.example.service;

import com.example.entity.FacePicture;
import com.example.entity.Member;
import com.example.repository.FacePictureRepository;
import com.example.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacePictureService {
    private final FacePictureRepository facePictureRepository;
    private final MemberRepository memberRepository;

    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    public String jwtTokenToPayload(String authorization) {
        String jwtToken = authorization.replace("Bearer", "");

        String secretKey = jwtSecretKey;

        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build();

        Claims claims = parser.parseClaimsJws(jwtToken).getBody();

        return claims.getSubject();
    }

    public FacePicture saveFacePicture(byte[] forehead, byte[] leftCheek, byte[] rightCheek, byte[] chin, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        FacePicture facePicture = FacePicture.builder()
                .forehead(forehead)
                .left_cheek(leftCheek)
                .right_cheek(rightCheek)
                .chin(chin)
                .member(member)
                .build();

        return facePictureRepository.save(facePicture);
    }
}
