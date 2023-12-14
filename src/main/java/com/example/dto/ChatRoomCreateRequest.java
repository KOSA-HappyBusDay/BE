package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomCreateRequest {

    private Long id;

    private String name;

    private Long memberId;

    private Long clinicMemberId;

    // getter, setter, 생성자 등


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getClinicMemberId() {
        return clinicMemberId;
    }

    public void setClinicMemberId(Long clinicMemberId) {
        this.clinicMemberId = clinicMemberId;
    }
}