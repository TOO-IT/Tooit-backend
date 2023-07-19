package com.kr.tooit.domain.vote.service;

import com.kr.tooit.domain.vote.domain.QVote;
import com.kr.tooit.domain.vote.domain.Vote;
import com.kr.tooit.domain.vote.dto.VoteListResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class VoteRepositoryPage {

    private final JPAQueryFactory queryFactory;

    public List<Vote> getSlice(Long lastVoteId, Pageable pageable) {
        QVote vote = new QVote("v");
        List<Vote> results = queryFactory.selectFrom(vote)
                .where(
                        ltVoteId(vote, lastVoteId),
                        vote.deadlineStatus.eq("N"),
                        vote.deleteStatus.eq("N"),
                        vote.shareTarget.eq("A")
                )
                .orderBy(vote.id.desc())
                .limit(pageable.getPageSize() +1)
                .fetch();

        return results;
    }

    public List<Vote> getSliceOrderByVoteCount(Pageable pageable) {
        QVote vote = new QVote("v");
        List<Vote> results = queryFactory.selectFrom(vote)
                .where(
                        vote.deadlineStatus.eq("N"),
                        vote.deleteStatus.eq("N"),
                        vote.shareTarget.eq("A")
                )
                .orderBy(vote.voteCount.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() +1)
                .fetch();
        return  results;
    }

    public List<Vote> getSliceMyVotes(Long id, Pageable pageable) {
        QVote vote = new QVote("v");
        List<Vote> results = queryFactory.selectFrom(vote)
                .where(
                        vote.deadlineStatus.eq("N"),
                        vote.deleteStatus.eq("N"),
                        vote.shareTarget.eq("A"),
                        vote.user.id.eq(id)
                )
                .orderBy(vote.id.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();
        return results;
    }

    private BooleanExpression ltVoteId(QVote vote, Long voteId) {
        if (voteId == null) {
            return null;
        }

        return vote.id.lt(voteId);
    }


}
