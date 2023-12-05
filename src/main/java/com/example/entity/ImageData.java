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
@Table(name = "Image_Data")
@Getter
@Setter
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @Lob
    private byte[] image_data;

    private String name;

    @ManyToOne
    @JoinColumn(name = "FacepPctures_id")
    private FacePicture facePicture;

    private String result_class;

    @Builder
    public ImageData(String name, String type, byte[] imageData, FacePicture facePicture) {
        this.name = name;
        this.type = type;
        this.image_data = imageData;
        this.facePicture = facePicture;
    }
}