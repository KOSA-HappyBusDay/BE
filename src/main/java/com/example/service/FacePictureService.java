package com.example.service;

import com.example.dto.FacePictureResponse;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public FacePicture saveFacePicture(byte[] forehead, byte[] leftCheek, byte[] rightCheek, byte[] chin,
                                       String email, String AImessage) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        FacePicture facePicture = FacePicture.builder()
                .forehead(forehead)
                .left_cheek(leftCheek)
                .right_cheek(rightCheek)
                .chin(chin)
                .member(member)
                .AImessage(AImessage)
                .build();

        return facePictureRepository.save(facePicture);
    }

    public List<FacePictureResponse> getFacePicturesByMemberId(Long memberId) {
        List<FacePicture> facePictures = facePictureRepository.findByMemberId(memberId);
        List<FacePictureResponse> responseList = new ArrayList<>();
        for (FacePicture facePicture : facePictures) {
            FacePictureResponse response = new FacePictureResponse(
                    facePicture.getId(),
                    facePicture.getForehead(),
                    facePicture.getLeft_cheek(),
                    facePicture.getRight_cheek(),
                    facePicture.getChin(),
                    facePicture.getAImessage(),
                    facePicture.getLocalDate()
            );
            responseList.add(response);
        }
        return responseList;
    }

    public FacePictureResponse getFacePictureById(Long facePictureId) {
        Optional<FacePicture> facePictureOptional = facePictureRepository.findById(facePictureId);
        if (facePictureOptional.isPresent()) {
            FacePicture facePicture = facePictureOptional.get();
            FacePictureResponse response = new FacePictureResponse(
                    facePicture.getId(),
                    facePicture.getForehead(),
                    facePicture.getLeft_cheek(),
                    facePicture.getRight_cheek(),
                    facePicture.getChin(),
                    facePicture.getAImessage(),
                    facePicture.getLocalDate()
            );
//            System.out.println("FacePictureResponse: " + response); // Logging
            return response;
        } else {
            throw new RuntimeException("FacePicture not found");
        }
    }

    public byte[] getImageBytes(String type, Long id) {
        FacePicture facePicture = facePictureRepository.findById(id).orElse(null);

        if (facePicture == null) {
            return null;
        }

        switch (type.toLowerCase()) {
            case "forehead":
                return facePicture.getForehead();
            case "leftcheek":
                return facePicture.getLeft_cheek();
            case "rightcheek":
                return facePicture.getRight_cheek();
            case "chin":
                return facePicture.getChin();
            default:
                return null;
        }
    }
}