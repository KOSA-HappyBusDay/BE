package com.example.repository;

import com.example.entity.MediaData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<MediaData, Long> {

    Optional<MediaData> findByName(String name);
    // methods
}
