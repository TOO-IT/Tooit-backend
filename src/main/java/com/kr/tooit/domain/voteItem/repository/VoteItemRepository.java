package com.kr.tooit.domain.voteItem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kr.tooit.domain.voteItem.domain.VoteItem;

@Repository
public interface VoteItemRepository extends JpaRepository<VoteItem, Long> {

    @Modifying
    @Query("update VoteItem set stickerCount = stickerCount + 1 where id = :voteItemId")
    void updateStickerCount(Long voteItemId);
}
