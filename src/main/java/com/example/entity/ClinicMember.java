package com.example.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ClinicMembers")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClinicMember extends User{

    @Column(unique=true)
    private String clinicEmail;
    private String clinicName;
    private String registNumber;
    private String clinicAddress;
    private String role;

    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "clinicMember")
    @JsonIgnore
    private List<ChatRoom> chatRooms = new ArrayList<>();

    @OneToMany(mappedBy = "clinic_member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> authorities = new ArrayList<>();
}
