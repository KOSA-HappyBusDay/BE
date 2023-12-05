////package com.example.service;
////
////import com.example.userdetails.JwtProvider;
////import com.example.dto.SignInRequestDto;
////import com.example.dto.SignInResponseDto;
////import com.example.entity.Authority;
////import com.example.repository.MemberRepository;
////import lombok.RequiredArgsConstructor;
////import org.springframework.security.authentication.BadCredentialsException;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.stereotype.Service;
////import com.example.entity.Member;
////
////import javax.transaction.Transactional;
////import java.util.Collections;
////
////@Service
////@Transactional
////@RequiredArgsConstructor
////public class SignInService {
////    private final MemberRepository memberRepository;
////    private final AuthorityService authorityService;
////    private final PasswordEncoder passwordEncoder;
////    private final JwtProvider jwtProvider;
////
////    private static final String DEFAULT_ROLE_USER = "ROLE_USER";
////
////    public SignInResponseDto login(SignInRequestDto request) throws Exception {
////        Member member = memberRepository.findByEmail(request.getEmail()).orElseThrow(() ->
////                new BadCredentialsException("잘못된 계정정보입니다."));
////
////        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
////            throw new BadCredentialsException("잘못된 계정정보입니다.");
////        }
////
////        return SignInResponseDto.builder()
////                .id(member.getId())
////                .email(member.getEmail())
////                .membername(member.getMemberName())
////                .authorities(member.getAuthorities())
////                .token(jwtProvider.createToken(member.getEmail(), member.getAuthorities()))
////                .build();
////    }
////
////    public boolean register(SignInRequestDto request) throws Exception {
////        try {
////            Member member = Member.builder()
////                    .password(passwordEncoder.encode(request.getPassword()))
////                    .memberName(request.getMembername())
////                    .email(request.getEmail())
////                    .address(request.getAddress())
////                    .authorities(Collections.singletonList(Authority.builder().name("ROLE_USER").member(member).build()))
////                    .build();
////
////            // Set authorities directly to the member entity
////            member.setAuthorities(Collections.singletonList(Authority.builder().name("ROLE_USER").member(member).build()));
////
////            memberRepository.save(member);
////        } catch (Exception e) {
////            System.out.println(e.getMessage());
////            throw new Exception("잘못된 요청입니다.");
////        }
////        return true;
////    }
////
////    public SignInResponseDto getMember(String email) throws Exception {
////        Member member = memberRepository.findByEmail(email)
////                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));
////        return new SignInResponseDto(member);
////    }
////}
//package com.example.service;
//
//import com.example.userdetails.JwtProvider;
//import com.example.dto.SignInRequestDto;
//import com.example.dto.SignInResponseDto;
//import com.example.entity.Authority;
//import com.example.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import com.example.entity.Member;
//
//import javax.transaction.Transactional;
//import java.util.Collections;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class SignInService {
//    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtProvider jwtProvider;
//
//    private static final String DEFAULT_ROLE_USER = "ROLE_USER";
//
//    public SignInResponseDto login(SignInRequestDto request) throws Exception {
//        Member member = memberRepository.findByEmail(request.getEmail()).orElseThrow(() ->
//                new BadCredentialsException("잘못된 계정정보입니다."));
//
//        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
//            throw new BadCredentialsException("잘못된 계정정보입니다.");
//        }
//
//        return SignInResponseDto.builder()
//                .id(member.getId())
//                .nickname(member.getNickname())
//                .name(member.getName())
//                .gender(member.getGender())
//                .skintype(member.getSkintype())
//                .birthday(member.getBirthday())
//                .email(member.getEmail())
//                .username(member.getUsername())
//                .password(member.getPassword())
//                .passwordtest(member.getPasswordtest())
//                .authorities(member.getAuthorities())
//                .facePictures(member.getFacepPctures())
//                .token(jwtProvider.createToken(member.getEmail(), member.getAuthorities()))
//                .build();
//    }
//    public boolean register(SignInRequestDto request) throws Exception {
//        try {
//            Member member = Member.builder()
//                    .password(passwordEncoder.encode(request.getPassword()))
//                    .nickname(request.getNickname())
//                    .email(request.getEmail())
//                    .authorities(Collections.singletonList(Authority.builder().name(DEFAULT_ROLE_USER).member(member).build()))
//                    .build();
//
//            // Set authorities directly to the member entity
//            member.setAuthorities(Collections.singletonList(Authority.builder().name(DEFAULT_ROLE_USER).member(member).build()));
//
//            memberRepository.save(member);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            throw new Exception("잘못된 요청입니다.");
//        }
//        return true;
//    }
//
//    public SignInResponseDto getMember(String email) throws Exception {
//        Member member = memberRepository.findByEmail(email)
//                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));
//        return SignInResponseDto.from(member);
//    }
//}
package com.example.service;

import com.example.userdetails.JwtProvider;
import com.example.dto.SignInRequestDto;
import com.example.dto.SignInResponseDto;
import com.example.entity.Authority;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.entity.Member;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class SignInService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    private static final String DEFAULT_ROLE_USER = "ROLE_USER";

    public SignInResponseDto login(SignInRequestDto request) throws Exception {
        Member member = memberRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new BadCredentialsException("잘못된 계정정보입니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }

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
                .token(jwtProvider.createToken(member.getEmail(), member.getAuthorities()))
                .build();
    }

    public boolean register(SignInRequestDto request) throws Exception {
        try {
            Member member = Member.builder()
                    .password(passwordEncoder.encode(request.getPassword()))
                    .nickname(request.getNickname())
                    .email(request.getEmail())
                    .build();

            // Set authorities directly to the member entity
            member.setAuthorities(Collections.singletonList(Authority.builder().name(DEFAULT_ROLE_USER).member(member).build()));

            memberRepository.save(member);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }
    public SignInResponseDto getMember(String email) throws Exception {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));
        return SignInResponseDto.from(member);
    }
}
