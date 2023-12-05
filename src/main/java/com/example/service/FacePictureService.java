package com.example.service;

import com.example.entity.FacePicture;
import com.example.entity.Member;
import com.example.repository.FacePictureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacePictureService {
    private final FacePictureRepository facePictureRepository;

    public FacePicture saveFacePicture(Member member, Integer forehead, Integer leftCheek, Integer rightCheek, Integer chin) {
        return facePictureRepository.save(
                FacePicture.builder()
                        .forehead(forehead)
                        .left_cheek(leftCheek)
                        .right_cheek(rightCheek)
                        .chin(chin)
                        .member(member)
                        .build()
        );
    }
}
