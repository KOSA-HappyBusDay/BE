package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDto {
    private Long id;

    private String membername;

    private String password;

    private String email;

    private String address;  // 이 부분을 추가해주세요
}

