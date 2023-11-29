package com.example.entity;

//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "ImageData")
//@Getter
//@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class ImageData {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    private String type;
//
//    @Lob
//    @Column(name = "imagedata", length = 1000)
//    private byte[] imageData;
//
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;
//
//    @Builder
//    public ImageData(String name, String type, byte[] imageData, Member member) {
//        this.name = name;
//        this.type = type;
//        this.imageData = imageData;
//        this.member = member;
//    }
//}

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ImageData")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="socialuser_id")
    private SocialUser socialUser;

    @Column
    private String plate;

    @Builder
    public ImageData(Long id, String name, String type, byte[] imageData, Member member, SocialUser socialUser, String plate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imageData = imageData;
        this.member = member;
        this.socialUser = socialUser;
        this.plate = plate;
    }
}
