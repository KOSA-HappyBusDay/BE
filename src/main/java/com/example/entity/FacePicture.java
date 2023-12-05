package com.example.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class FacePicture{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer forehead;
    private Integer left_cheek;
    private Integer right_cheek;
    private Integer chin;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "facePicture", cascade = CascadeType.ALL)
    private List<ImageData> imageDataList;

    @Builder
    public FacePicture(Integer forehead, Integer left_cheek, Integer right_cheek, Integer chin, Member member, List<ImageData> imageDataList) {
        this.forehead = forehead;
        this.left_cheek = left_cheek;
        this.right_cheek = right_cheek;
        this.chin = chin;
        this.member = member;
        this.imageDataList = imageDataList;
    }
}

