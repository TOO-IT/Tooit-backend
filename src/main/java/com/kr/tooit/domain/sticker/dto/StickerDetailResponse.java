package com.kr.tooit.domain.sticker.dto;

import com.kr.tooit.domain.sticker.domain.Sticker;
import lombok.Getter;

@Getter
public class StickerDetailResponse {
    private Long id;
    private String image;
    private String locationX;
    private String locationY;
    private String nickname;
    private String content;
    private Long userId;
    private String ip;


    public StickerDetailResponse(Sticker sticker) {
        this.id = sticker.getId();
        this.image = sticker.getImage();
        this.locationX = sticker.getLocationX();
        this.locationY = sticker.getLocationY();
        this.nickname = sticker.getNickname();
        this.content = sticker.getContent();
        this.userId = sticker.getUser() == null ? null : sticker.getUser().getId();
        this.ip = sticker.getNonUser() == null ? null : sticker.getNonUser().getIp();
    }
}
