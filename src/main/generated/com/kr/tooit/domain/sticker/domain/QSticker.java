package com.kr.tooit.domain.sticker.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSticker is a Querydsl query type for Sticker
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSticker extends EntityPathBase<Sticker> {

    private static final long serialVersionUID = 408752901L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSticker sticker = new QSticker("sticker");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final StringPath locationX = createString("locationX");

    public final StringPath locationY = createString("locationY");

    public final StringPath nickname = createString("nickname");

    public final com.kr.tooit.domain.user.domain.QNonUsers nonUser;

    public final com.kr.tooit.domain.user.domain.QUser user;

    public final NumberPath<Long> voteId = createNumber("voteId", Long.class);

    public final com.kr.tooit.domain.voteItem.domain.QVoteItem voteItem;

    public QSticker(String variable) {
        this(Sticker.class, forVariable(variable), INITS);
    }

    public QSticker(Path<? extends Sticker> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSticker(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSticker(PathMetadata metadata, PathInits inits) {
        this(Sticker.class, metadata, inits);
    }

    public QSticker(Class<? extends Sticker> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nonUser = inits.isInitialized("nonUser") ? new com.kr.tooit.domain.user.domain.QNonUsers(forProperty("nonUser")) : null;
        this.user = inits.isInitialized("user") ? new com.kr.tooit.domain.user.domain.QUser(forProperty("user")) : null;
        this.voteItem = inits.isInitialized("voteItem") ? new com.kr.tooit.domain.voteItem.domain.QVoteItem(forProperty("voteItem"), inits.get("voteItem")) : null;
    }

}

