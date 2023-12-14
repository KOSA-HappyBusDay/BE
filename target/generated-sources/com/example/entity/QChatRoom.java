package com.example.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRoom is a Querydsl query type for ChatRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatRoom extends EntityPathBase<ChatRoom> {

    private static final long serialVersionUID = 773788717L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatRoom chatRoom = new QChatRoom("chatRoom");

    public final QClinicMember clinicMember;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final ListPath<Message, QMessage> messages = this.<Message, QMessage>createList("messages", Message.class, QMessage.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QChatRoom(String variable) {
        this(ChatRoom.class, forVariable(variable), INITS);
    }

    public QChatRoom(Path<? extends ChatRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatRoom(PathMetadata metadata, PathInits inits) {
        this(ChatRoom.class, metadata, inits);
    }

    public QChatRoom(Class<? extends ChatRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clinicMember = inits.isInitialized("clinicMember") ? new QClinicMember(forProperty("clinicMember")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

