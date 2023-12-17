package com.example.service;

import com.example.entity.ChatRoom;
import com.example.entity.ClinicMember;
import com.example.entity.Member;
import com.example.repository.ChatRoomRepository;
import com.example.repository.ClinicMemberRepository;
import com.example.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final ClinicMemberRepository clinicMemberRepository;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository, MemberRepository memberRepository, ClinicMemberRepository clinicMemberRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.memberRepository = memberRepository;
        this.clinicMemberRepository = clinicMemberRepository;
    }

    public ChatRoom createOrGetChatRoom(Long memberId, Long clinicMemberId, String chatRoomName) {
        Optional<ChatRoom> existingRoom = chatRoomRepository.findByMemberIdAndClinicMemberId(memberId, clinicMemberId);
        if (existingRoom.isPresent()) {
            return existingRoom.get();
        } else {
            // 채팅방 생성 로직
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid member Id"));
            ClinicMember clinicMember = clinicMemberRepository.findById(clinicMemberId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid clinicMember Id"));

            ChatRoom chatRoom = ChatRoom.builder()
                    .member(member)
                    .clinicMember(clinicMember)
                    .name(chatRoomName) // 채팅방 이름 설정
                    .build();

            return chatRoomRepository.save(chatRoom);
        }
    }

    public ChatRoom getChatRoom(Long memberId, Long clinicMemberId) {
        return chatRoomRepository.findByMemberIdAndClinicMemberId(memberId, clinicMemberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member Id or clinicMember Id"));
    }

    public List<ChatRoom> getChatRooms() {
        return chatRoomRepository.findAll(); // 모든 채팅방을 반환
    }
}