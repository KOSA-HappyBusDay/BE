package com.example.repository;
import com.example.entity.FacePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacePictureRepository extends JpaRepository<FacePicture, Long> {
    List<FacePicture> findByMemberId(Long memberId);
}
