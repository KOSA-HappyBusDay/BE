package com.example.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(columnDefinition = "LONGBLOB")
    @Lob
    private String AImessage;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @CreationTimestamp
    @Column
    private LocalDate localDate;


    @Builder
    public FacePicture(byte[] forehead, byte[] left_cheek, byte[] right_cheek, byte[] chin,
                       Member member, String AImessage, LocalDate localDate) {
        this.forehead = forehead;
        this.left_cheek = left_cheek;
        this.right_cheek = right_cheek;
        this.chin = chin;
        this.member = member;
        this.AImessage = AImessage;
        this.localDate = localDate;

    }
}