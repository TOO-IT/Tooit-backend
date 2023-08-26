package com.kr.tooit.domain.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNonUsers is a Querydsl query type for NonUsers
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNonUsers extends EntityPathBase<NonUsers> {

    private static final long serialVersionUID = -736690781L;

    public static final QNonUsers nonUsers = new QNonUsers("nonUsers");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final StringPath ip = createString("ip");

    public QNonUsers(String variable) {
        super(NonUsers.class, forVariable(variable));
    }

    public QNonUsers(Path<? extends NonUsers> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNonUsers(PathMetadata metadata) {
        super(NonUsers.class, metadata);
    }

}

