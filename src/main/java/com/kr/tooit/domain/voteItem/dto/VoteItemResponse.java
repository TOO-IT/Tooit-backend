package com.kr.tooit.domain.voteItem.dto;

import com.kr.tooit.domain.voteItem.domain.VoteItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteItemResponse {

    private Long id;
    private String image;
    private int stickerCount;
    private String name;
    private String content;
    private Long voteId;

    public VoteItemResponse(VoteItem entity) {
        this.id = entity.getId();
        this.image = entity.getImage();
        this.stickerCount = entity.getStickerCount();
        this.name = entity.getName();
        this.content = entity.getContent();
        this.voteId = entity.getVote().getId();
    }

}
