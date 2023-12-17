package com.example.dto;

import com.example.entity.Authority;
import com.example.entity.ChatRoom;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClinicMemberSignInRequestDto {
    private Long id;
    private String clinicEmail;
    private String clinicName;
    private String registNumber;
    private String role;
    private String password;
    private String clinicAddress;
    private String token;
    private List<Long> chatRoomIds = new ArrayList<>();
    private List<Long> authorityIds = new ArrayList<>();
}
