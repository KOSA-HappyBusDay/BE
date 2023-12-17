package com.example.service;

import com.example.userdetails.JwtProvider;
import com.example.dto.SignInRequestDto;
import com.example.dto.SignInResponseDto;
import com.example.entity.Authority;
import com.example.entity.Member;
import com.example.entity.FacePicture;
import com.example.entity.ChatRoom;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SignInService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    private static final String DEFAULT_ROLE_USER = "ROLE_USER";

    public SignInResponseDto login(SignInRequestDto request) throws Exception {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("잘못된 계정정보입니다."));

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
                .authorityIds(member.getAuthorities().stream().map(Authority::getId).collect(Collectors.toList()))
                .facePictureIds(member.getFacePictures().stream().map(FacePicture::getId).collect(Collectors.toList()))
                .chatRoomIds(member.getChatRooms().stream().map(ChatRoom::getId).collect(Collectors.toList()))
                .address(member.getAddress())
                .token(jwtProvider.createToken(member.getEmail(), member.getAuthorities()))
                .build();
    }


    public boolean register(SignInRequestDto request) throws Exception {
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new Exception("이미 사용 중인 이메일입니다.");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        Member member = Member.builder()
                .birthday(request.getBirthday())
                .email(request.getEmail())
                .gender(request.getGender())
                .name(request.getName())
                .skintype(request.getSkintype())
                .password(hashedPassword)
                .nickname(request.getNickname())
                .username(request.getUsername())
                .address(request.getAddress())
                .build();

        member = memberRepository.save(member);  // Member 저장

        Authority authority = Authority.builder().name(DEFAULT_ROLE_USER).member(member).build();

        // ArrayList를 사용하여 authorities 필드 설정
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);
        member.setAuthorities(authorities);

        memberRepository.save(member);  // Member 업데이트

        return true;
    }


    public SignInResponseDto getMember(String email) throws Exception {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));
        return SignInResponseDto.from(member);
    }

    public SignInResponseDto updateMemberInfo(String email, SignInRequestDto request) throws Exception {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));

        member.update(request.getName(), request.getNickname(), request.getGender(), request.getSkintype(), request.getBirthday(), request.getAddress(), request.getPassword(), passwordEncoder);

        Member updatedMember = memberRepository.save(member);

        return SignInResponseDto.from(updatedMember);
    }



    public SignInResponseDto getMemberInfo(String email) throws Exception {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));

        return SignInResponseDto.from(member);
    }

}