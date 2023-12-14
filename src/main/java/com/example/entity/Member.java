package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Member extends User{

    @Column(length = 45)
    private String nickname;

    @Column(length = 45)
    private String name;

    @Column(length = 45)
    private String gender;

    @Column(length = 45)
    private String skintype;

    @Column
    private Date birthday;

    @Column(nullable = false,length = 45, unique = true)
    private String email;

    @Column(length = 45)
    private String username;

    @JsonIgnore
    @Column(nullable = false,length = 245)
    private String password;

    @Column(length = 245)
    private String address;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> authorities = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<FacePicture> facePictures = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<ChatRoom> chatRooms;
}
