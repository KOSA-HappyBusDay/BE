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

    public ChatRoom createChatRoom(String name, Long memberId, Long clinicMemberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Optional<ClinicMember> optionalClinicMember = clinicMemberRepository.findById(clinicMemberId);

        // 새로운 채팅방 생성 전에 member와 clinicMember가 null이 아닌지 확인
        if (optionalMember.isEmpty() || optionalClinicMember.isEmpty()) {
            throw new IllegalArgumentException("Invalid member Id or clinicMember Id");
        }
        Member member = optionalMember.get();
        ClinicMember clinicMember = optionalClinicMember.get();

        // 채팅방 생성
        ChatRoom chatRoom = ChatRoom.builder()
                .name(name)
                .member(member)
                .clinicMember(clinicMember)
                .build();

        // 채팅방 데이터베이스에 저장
        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom getChatRoom(Long memberId, Long clinicMemberId) {
        return chatRoomRepository.findByMemberIdAndClinicMemberId(memberId, clinicMemberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member Id or clinicMember Id"));
    }

    public List<ChatRoom> getChatRooms() {
        return chatRoomRepository.findAll(); // 모든 채팅방을 반환
    }
}