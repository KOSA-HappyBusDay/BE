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

<<<<<<< HEAD
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="socialuser_id")
    private SocialUser socialUser;


    @Builder
    public MediaData(String name, byte[] mediaData, String type, Member member, SocialUser socialUser) {
        this.name = name;
        this.mediaData = mediaData;
        this.type = type;
        this.member = member;
        this.socialUser = socialUser;
=======
    @Builder
    public MediaData(String name, byte[] mediaData, String type) {
        this.name = name;
        this.mediaData = mediaData;
        this.type = type;
>>>>>>> 351cf12c9eb57c1b1a7fb01db3fd6b1de2e39ed6
    }
}
