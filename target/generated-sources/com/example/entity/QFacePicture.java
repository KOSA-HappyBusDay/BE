package com.example.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFacePicture is a Querydsl query type for FacePicture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFacePicture extends EntityPathBase<FacePicture> {

    private static final long serialVersionUID = -519934521L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFacePicture facePicture = new QFacePicture("facePicture");

    public final ArrayPath<byte[], Byte> chin = createArray("chin", byte[].class);

    public final ArrayPath<byte[], Byte> forehead = createArray("forehead", byte[].class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ArrayPath<byte[], Byte> left_cheek = createArray("left_cheek", byte[].class);

    public final QMember member;

    public final ArrayPath<byte[], Byte> right_cheek = createArray("right_cheek", byte[].class);

    public QFacePicture(String variable) {
        this(FacePicture.class, forVariable(variable), INITS);
    }

    public QFacePicture(Path<? extends FacePicture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFacePicture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFacePicture(PathMetadata metadata, PathInits inits) {
        this(FacePicture.class, metadata, inits);
    }

    public QFacePicture(Class<? extends FacePicture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

