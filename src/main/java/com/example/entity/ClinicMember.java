package com.example.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ClinicMembers")
@Getter
@Setter
public class ClinicMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clinic_name;
    private String regist_number;
    private String role;
}