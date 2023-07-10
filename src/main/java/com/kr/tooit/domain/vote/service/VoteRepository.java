package com.kr.tooit.domain.vote.service;

import com.kr.tooit.domain.vote.domain.Vote;
import com.kr.tooit.domain.vote.dto.VoteListResponse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long>, JpaSpecificationExecutor<Vote> {

    List<Vote> findAll(Specification<Vote> spec);

}
