////package com.example.dto;
////
////import com.example.entity.Authority;
////import lombok.AllArgsConstructor;
////import lombok.Builder;
////import lombok.Getter;
////import lombok.NoArgsConstructor;
////import com.example.entity.Member;
////
////import java.util.ArrayList;
////import java.util.List;
////
////@Getter
////@Builder
////@AllArgsConstructor
////@NoArgsConstructor
////public class SignInResponseDto {
////    private Long id;
////
////    private String membername;
////
////    private String email;
////
////    private String address;
////
////    @Builder.Default
////    private List<Authority> roles = new ArrayList<>();
////
////    private String token;
////
////    public static SignInResponseDto from(Member member) {
////        return SignInResponseDto.builder()
////                .id(member.getId())
////                .membername(member.getMembername())
////                .email(member.getEmail())
////                .address(member.getAddress())
////                .roles(member.getRoles())
////                .build();
////    }
////}
//package com.example.dto;
//
//import com.example.entity.Authority;
//import com.example.entity.Member;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
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
//    private String membername;
//    private String email;
//    private String address;
//    private List<Authority> authorities = new ArrayList<>();
//    private String token;
//
//    // Constructor that accepts a Member object
//    public SignInResponseDto(Member member) {
//        this.id = member.getId();
//        this.membername = member.getMemberName();
//        this.email = member.getEmail();
//        this.address = member.getAddress();
//        this.authorities = member.getAuthorities();
//    }
//}
package com.example.dto;

import com.example.entity.Authority;
import com.example.entity.FacePicture;
import com.example.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {
    private Long id;
    private String nickname;
    private String name;
    private String gender;
    private String skintype;
    private Date birthday;
    private String email;
    private String username;
    private String password;
    private String passwordtest;
    private List<Authority> authorities = new ArrayList<>();
    private List<FacePicture> facePictures = new ArrayList<>();
    private String token;

    public static SignInResponseDto from(Member member) {
        return SignInResponseDto.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .name(member.getName())
                .gender(member.getGender())
                .skintype(member.getSkintype())
                .birthday(member.getBirthday())
                .email(member.getEmail())
                .username(member.getUsername())
                .password(member.getPassword())
                .passwordtest(member.getPasswordtest())
                .authorities(member.getAuthorities())
                .facePictures(member.getFacePictures())
                .build();
    }
}
