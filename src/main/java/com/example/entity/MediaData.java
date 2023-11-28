package com.example.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class MediaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false)
    private byte[] mediaData;

    @Column(nullable = false)
    private String type;

    @Builder
    public MediaData(String name, byte[] mediaData, String type) {
        this.name = name;
        this.mediaData = mediaData;
        this.type = type;
    }
}
