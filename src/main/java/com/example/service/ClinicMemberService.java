package com.example.service;

import com.example.dto.ClinicMemberSignInRequestDto;
import com.example.dto.ClinicMemberSignInResponseDto;
import com.example.entity.ClinicMember;
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
        ClinicMember clinicMember = clinicMemberRepository.findByClinicEmail(request.getClinicEmail());
        if (clinicMember == null || !passwordEncoder.matches(request.getPassword(), clinicMember.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

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

}
