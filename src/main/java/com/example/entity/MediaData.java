//package com.example.entity;
//
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//@NoArgsConstructor
//public class MediaData {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Lob
//    @Column(nullable = false)
//    private byte[] mediaData;
//
//    @Column(nullable = false)
//    private String type;
//
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;
//
//    @ManyToOne
//    @JoinColumn(name="socialuser_id")
//    private SocialUser socialUser;
//
//    private String videoDuration; // videoDuration 추가
//
//
//    @Builder
//    public MediaData(String name, byte[] mediaData, String type, Member member, SocialUser socialUser,String videoDuration) {
//        this.name = name;
//        this.mediaData = mediaData;
//        this.type = type;
//        this.member = member;
//        this.socialUser = socialUser;
//        this.videoDuration = videoDuration;
//    }
//}
