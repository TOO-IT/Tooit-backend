package com.kr.tooit.domain.voteItem.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kr.tooit.domain.voteItem.domain.VoteItem;

@Repository
public interface VoteItemRepository extends JpaRepository<VoteItem, Long> {
}
