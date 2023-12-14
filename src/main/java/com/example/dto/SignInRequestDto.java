package com.example.dto;

import com.example.entity.Authority;
import com.example.entity.ChatRoom;
import com.example.entity.FacePicture;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SignInRequestDto {
    private Long id;
    private String nickname;
    private String name;
    private String gender;
    private String skintype;
    private Date birthday;
    private String email;
    private String username;
    private String password;
    private List<Authority> authorities = new ArrayList<>();
    private List<FacePicture> facePictures = new ArrayList<>();
    private List<ChatRoom> chatRooms = new ArrayList<>();
    private String token;
    private String address;
}
