package com.example.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false, length=45)
    private String nickname;

    @Column(length=45)
    private String name;

    @Column(length=45)
    private String gender;

    @Column(length=45)
    private String skintype;

    @Column
    private Date birthday;

    @Column(length=45)
    private String email;

    @Column(length=45)
    private String username;

    @Column(length=45)
    private String password;

    @Column(length=45)
    private String passwordtest;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> authorities = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<FacePicture> facePictures = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<ChatMessage> chatmessages = new ArrayList<>();

    // 다른 필드 및 메서드들은 생략하였습니다.

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setFacepPctures(List<FacePicture> facepPctures) {
        this.facePictures = facepPctures;
    }

    public void setChatmessages(List<ChatMessage> chatmessages) {
        this.chatmessages = chatmessages;
    }
}