package com.example.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false, length=50)
    private String memberName;

    @Column(nullable=false, length=100, unique=true)
    private String email;

    @Column(nullable=false, length=200)
    private String password;

//    @Column(nullable=false, length=50)
//    private String name;

    // 명시적으로 getAddress와 setAddress 추가
    @Getter
    @Column(length=100)
    private String address;

//    @Column(length=200)
//    private String medical_certification;

    // 명시적으로 getAuthorities와 setAuthorities 추가
    @Getter
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> authorities = new ArrayList<>();

    public Member(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<ImageData> images = new ArrayList<>();

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
