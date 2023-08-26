package com.kr.tooit.domain.vote.service;

import com.kr.tooit.domain.vote.domain.Vote;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long>, JpaSpecificationExecutor<Vote> {

    List<Vote> findAll(Specification<Vote> spec);

    @Modifying
    @Query("update Vote set voteCount = voteCount + 1 where id = :voteId")
    void updateVoteCount(Long voteId);

    @Modifying
    @Query("update Vote set voteCount = voteCount - 1 where id = :voteId")
    void decreaseVoteCount(Long voteId);
}
