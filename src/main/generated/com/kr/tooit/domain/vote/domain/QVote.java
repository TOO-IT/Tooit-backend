package com.kr.tooit.domain.vote.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVote is a Querydsl query type for Vote
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVote extends EntityPathBase<Vote> {

    private static final long serialVersionUID = -4863503L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVote vote = new QVote("vote");

    public final StringPath content = createString("content");

    public final StringPath createdDate = createString("createdDate");

    public final NumberPath<Integer> dDay = createNumber("dDay", Integer.class);

    public final StringPath deadlineStatus = createString("deadlineStatus");

    public final StringPath deleteStatus = createString("deleteStatus");

    public final StringPath endDate = createString("endDate");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.kr.tooit.domain.voteItem.domain.VoteItem, com.kr.tooit.domain.voteItem.domain.QVoteItem> items = this.<com.kr.tooit.domain.voteItem.domain.VoteItem, com.kr.tooit.domain.voteItem.domain.QVoteItem>createList("items", com.kr.tooit.domain.voteItem.domain.VoteItem.class, com.kr.tooit.domain.voteItem.domain.QVoteItem.class, PathInits.DIRECT2);

    public final StringPath modifiedDate = createString("modifiedDate");

    public final com.kr.tooit.domain.review.domain.QReview review;

    public final StringPath shareTarget = createString("shareTarget");

    public final StringPath startDate = createString("startDate");

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public final com.kr.tooit.domain.user.domain.QUser user;

    public final NumberPath<Integer> voteCount = createNumber("voteCount", Integer.class);

    public QVote(String variable) {
        this(Vote.class, forVariable(variable), INITS);
    }

    public QVote(Path<? extends Vote> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVote(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVote(PathMetadata metadata, PathInits inits) {
        this(Vote.class, metadata, inits);
    }

    public QVote(Class<? extends Vote> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new com.kr.tooit.domain.review.domain.QReview(forProperty("review")) : null;
        this.user = inits.isInitialized("user") ? new com.kr.tooit.domain.user.domain.QUser(forProperty("user")) : null;
    }

}

