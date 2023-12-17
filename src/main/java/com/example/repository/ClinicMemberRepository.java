package com.example.repository;

import com.example.entity.ClinicMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicMemberRepository extends JpaRepository<ClinicMember, Long> {

    ClinicMember findByClinicName(String clinicName);
    Optional<ClinicMember> findByClinicEmail(String email);
    ClinicMember findByClinicAddress(String clinicAddress);
}