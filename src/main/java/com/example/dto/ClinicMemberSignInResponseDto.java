package com.example.dto;

import com.example.entity.ClinicMember;
import com.example.entity.Authority;
import com.example.entity.ChatRoom;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicMemberSignInResponseDto {
    private Long id;
    private String clinicEmail;
    private String clinicName;
    private String registNumber;
    private String role;
    private String token;
    private String clinicAddress;
    private List<Long> chatRoomIds;
    private List<Long> authorityIds;

    public static ClinicMemberSignInResponseDto from(ClinicMember clinicMember) {
        ClinicMemberSignInResponseDto responseDto = new ClinicMemberSignInResponseDto();
        responseDto.id = clinicMember.getId(); // 클리닉 멤버 아이디 설정
        responseDto.clinicEmail = clinicMember.getClinicEmail();
        responseDto.clinicName = clinicMember.getClinicName();
        responseDto.registNumber = clinicMember.getRegistNumber();
        responseDto.role = clinicMember.getRole();
        responseDto.authorityIds = clinicMember.getAuthorities().stream().map(Authority::getId).collect(Collectors.toList());
        responseDto.chatRoomIds = clinicMember.getChatRooms().stream().map(ChatRoom::getId).collect(Collectors.toList());
        responseDto.clinicAddress = clinicMember.getClinicAddress();
        return responseDto;
    }

}
