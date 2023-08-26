package com.kr.tooit.domain.sticker.service;

import com.kr.tooit.domain.sticker.domain.Sticker;
import com.querydsl.core.annotations.QueryTransient;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StickerRepository extends JpaRepository<Sticker, Long>, JpaSpecificationExecutor<Sticker> {
    List<Sticker> findAll(Specification<Sticker> spec);

    Sticker findByUserIdAndVoteItemId(Long userId, Long voteItemId);

    Sticker findByNonUserIpAndVoteItemId(String ip, Long id);
}
