package com.kr.tooit.domain.voteItem.dto;

import com.kr.tooit.domain.sticker.dto.StickerDetailResponse;
import com.kr.tooit.domain.voteItem.domain.VoteItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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

    private List<StickerDetailResponse> stickerList;

    public VoteItemResponse(VoteItem entity) {
        this.id = entity.getId();
        this.image = entity.getImage();
        this.stickerCount = entity.getStickerCount();
        this.name = entity.getName();
        this.content = entity.getContent();
        this.voteId = entity.getVote().getId();
        this.stickerList = entity.getStickers().stream().map(StickerDetailResponse::new).collect(Collectors.toList());
    }

    public void addStickerList(List<StickerDetailResponse> stickerList) {
        this.stickerList = stickerList;
    }

}
