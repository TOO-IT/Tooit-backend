package com.kr.tooit.domain.voteItem.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kr.tooit.domain.voteItem.image.VoteImage;

public interface VoteImageRepository extends JpaRepository<VoteImage, Long> {
}
