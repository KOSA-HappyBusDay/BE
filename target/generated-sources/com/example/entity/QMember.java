package com.example.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 879079668L;

    public static final QMember member = new QMember("member1");

    public final QUser _super = new QUser(this);

    public final StringPath address = createString("address");

    public final ListPath<Authority, QAuthority> authorities = this.<Authority, QAuthority>createList("authorities", Authority.class, QAuthority.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> birthday = createDateTime("birthday", java.util.Date.class);

    public final ListPath<ChatRoom, QChatRoom> chatRooms = this.<ChatRoom, QChatRoom>createList("chatRooms", ChatRoom.class, QChatRoom.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final ListPath<FacePicture, QFacePicture> facePictures = this.<FacePicture, QFacePicture>createList("facePictures", FacePicture.class, QFacePicture.class, PathInits.DIRECT2);

    public final StringPath gender = createString("gender");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath skintype = createString("skintype");

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

