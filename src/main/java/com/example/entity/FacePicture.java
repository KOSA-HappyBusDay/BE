package com.example.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor // 기본 생성자 추가
public class FacePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "LONGBLOB")
    @Lob
    private byte[] forehead;

    @Column(columnDefinition = "LONGBLOB")
    @Lob
    private byte[] left_cheek;

    @Column(columnDefinition = "LONGBLOB")
    @Lob
    private byte[] right_cheek;

    @Column(columnDefinition = "LONGBLOB")
    @Lob
    private byte[] chin;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public FacePicture(byte[] forehead, byte[] left_cheek, byte[] right_cheek, byte[] chin, Member member) {
        this.forehead = forehead;
        this.left_cheek = left_cheek;
        this.right_cheek = right_cheek;
        this.chin = chin;
        this.member = member;
    }
}

