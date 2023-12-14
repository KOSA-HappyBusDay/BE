package com.example.dto;

import com.example.entity.Member;
import com.example.entity.Authority;
import com.example.entity.ChatRoom;
import com.example.entity.FacePicture;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {
    private Long id;
    private String nickname;
    private String name;
    private String gender;
    private String skintype;
    private Date birthday;
    private String email;
    private String username;
    private List<Long> authorityIds;
    private List<Long> chatRoomIds;
    private List<Long> facePictureIds;
    private String token;
    private String address;

    public static SignInResponseDto from(Member member) {
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
                .build();
    }
}
