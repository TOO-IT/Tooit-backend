package com.kr.tooit.domain.voteItem.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVoteItem is a Querydsl query type for VoteItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVoteItem extends EntityPathBase<VoteItem> {

    private static final long serialVersionUID = 738776151L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVoteItem voteItem = new QVoteItem("voteItem");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> stickerCount = createNumber("stickerCount", Integer.class);

    public final com.kr.tooit.domain.vote.domain.QVote vote;

    public QVoteItem(String variable) {
        this(VoteItem.class, forVariable(variable), INITS);
    }

    public QVoteItem(Path<? extends VoteItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVoteItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVoteItem(PathMetadata metadata, PathInits inits) {
        this(VoteItem.class, metadata, inits);
    }

    public QVoteItem(Class<? extends VoteItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.vote = inits.isInitialized("vote") ? new com.kr.tooit.domain.vote.domain.QVote(forProperty("vote"), inits.get("vote")) : null;
    }

}

