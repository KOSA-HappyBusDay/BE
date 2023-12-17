package com.example.service;

import com.example.dto.ClinicMemberSignInRequestDto;
import com.example.dto.ClinicMemberSignInResponseDto;
import com.example.dto.SignInResponseDto;
import com.example.entity.ClinicMember;
import com.example.entity.Member;
import com.example.repository.AuthorityRepository;
import com.example.repository.ClinicMemberRepository;
import com.example.userdetails.JwtProvider;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicMemberService {
    @Autowired
    private ClinicMemberRepository clinicMemberRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    public ClinicMemberSignInResponseDto register(ClinicMemberSignInRequestDto request) {
        ClinicMember clinicMember = ClinicMember.builder()
                .clinicEmail(request.getClinicEmail())
                .clinicName(request.getClinicName())
                .registNumber(request.getRegistNumber())
                .role(request.getRole())
                .password(passwordEncoder.encode(request.getPassword()))
                .clinicAddress(request.getClinicAddress())
                .authorities(request.getAuthorityIds().stream()
                        .map(authorityId -> authorityRepository.findById(authorityId)
                                .orElseThrow(() -> new RuntimeException("Authority not found: " + authorityId)))
                        .collect(Collectors.toList()))
                .build();

        clinicMember = clinicMemberRepository.save(clinicMember);
        clinicMember.setChatRooms(new ArrayList<>());
        return ClinicMemberSignInResponseDto.from(clinicMember);
    }




    public ClinicMemberSignInResponseDto login(ClinicMemberSignInRequestDto request) throws InvalidCredentialsException {
        // findByClinicEmail의 반환 타입이 Optional<ClinicMember>이므로, orElseThrow를 사용하여 ClinicMember를 추출합니다.
        ClinicMember clinicMember = clinicMemberRepository.findByClinicEmail(request.getClinicEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        // 비밀번호 확인
        if (!passwordEncoder.matches(request.getPassword(), clinicMember.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // JWT 토큰 생성 및 응답 준비
        String token = jwtProvider.createToken(request.getClinicEmail(), clinicMember.getAuthorities());
        ClinicMemberSignInResponseDto responseDto = ClinicMemberSignInResponseDto.from(clinicMember);
        responseDto.setToken(token);
        return responseDto;
    }
    public List<ClinicMemberSignInResponseDto> getClinics() {
        List<ClinicMember> clinicMembers = clinicMemberRepository.findAll();
        return clinicMembers.stream()
                .map(ClinicMemberSignInResponseDto::from)
                .collect(Collectors.toList());
    }
    public ClinicMemberSignInResponseDto getClinicMemberInfo(String clinicEmail) throws Exception {
        ClinicMember clinicMember = clinicMemberRepository.findByClinicEmail(clinicEmail)
                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));

        return ClinicMemberSignInResponseDto.from(clinicMember);
    }

}