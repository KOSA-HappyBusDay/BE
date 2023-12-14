package com.example.repository;

import com.example.entity.ClinicMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicMemberRepository extends JpaRepository<ClinicMember, Long> {
    ClinicMember findByClinicEmail(String clinicEmail);  // 수정된 메소드
    ClinicMember findByClinicName(String clinicName);

    ClinicMember findByClinicAddress(String clinicAddress);
}