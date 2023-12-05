package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Chatmessages")
@Getter
@Setter
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date messages;
    private String send_at;

    @ManyToOne
    @JoinColumn(name = "ClinicMembers_id")
    private ClinicMember clinicMember;

    @ManyToOne
    @JoinColumn(name = "ChattingRoom_id")
    private ChattingRoom chattingRoom;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}