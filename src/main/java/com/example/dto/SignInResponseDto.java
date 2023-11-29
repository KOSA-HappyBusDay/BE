//package com.example.dto;
//
//import com.example.entity.Authority;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import com.example.entity.Member;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class SignInResponseDto {
//    private Long id;
//
//    private String membername;
//
//    private String email;
//
//    private String address;
//
//    @Builder.Default
//    private List<Authority> roles = new ArrayList<>();
//
//    private String token;
//
//    public static SignInResponseDto from(Member member) {
//        return SignInResponseDto.builder()
//                .id(member.getId())
//                .membername(member.getMembername())
//                .email(member.getEmail())
//                .address(member.getAddress())
//                .roles(member.getRoles())
//                .build();
//    }
//}
package com.example.dto;

import com.example.entity.Authority;
import com.example.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {
    private Long id;
    private String membername;
    private String email;
    private String address;
    private List<Authority> authorities = new ArrayList<>();
    private String token;

    // Constructor that accepts a Member object
    public SignInResponseDto(Member member) {
        this.id = member.getId();
        this.membername = member.getMemberName();
        this.email = member.getEmail();
        this.address = member.getAddress();
        this.authorities = member.getAuthorities();
    }
}
