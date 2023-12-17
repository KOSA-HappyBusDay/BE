package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @JsonIgnore
    @Builder.Default
    private List<FacePicture> facePictures = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<ChatRoom> chatRooms;

    public void update(String name, String nickname, String gender, String skintype, Date birthday, String address, String password, PasswordEncoder passwordEncoder) {
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        this.skintype = skintype;
        this.birthday = birthday;
        this.address = address;
        this.password = passwordEncoder.encode(password);
    }
}
