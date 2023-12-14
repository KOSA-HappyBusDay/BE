package com.example.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClinicMember is a Querydsl query type for ClinicMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClinicMember extends EntityPathBase<ClinicMember> {

    private static final long serialVersionUID = 1063303292L;

    public static final QClinicMember clinicMember = new QClinicMember("clinicMember");

    public final QUser _super = new QUser(this);

    public final ListPath<Authority, QAuthority> authorities = this.<Authority, QAuthority>createList("authorities", Authority.class, QAuthority.class, PathInits.DIRECT2);

    public final ListPath<ChatRoom, QChatRoom> chatRooms = this.<ChatRoom, QChatRoom>createList("chatRooms", ChatRoom.class, QChatRoom.class, PathInits.DIRECT2);

    public final StringPath clinicAddress = createString("clinicAddress");

    public final StringPath clinicEmail = createString("clinicEmail");

    public final StringPath clinicName = createString("clinicName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath password = createString("password");

    public final StringPath registNumber = createString("registNumber");

    public final StringPath role = createString("role");

    public QClinicMember(String variable) {
        super(ClinicMember.class, forVariable(variable));
    }

    public QClinicMember(Path<? extends ClinicMember> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClinicMember(PathMetadata metadata) {
        super(ClinicMember.class, metadata);
    }

}

