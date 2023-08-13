package com.kr.tooit.domain.sticker.dto;

import com.kr.tooit.domain.sticker.domain.Sticker;

public class StickerDetailResponse {
    private Long id;
    private String image;
    private String location;
    private String nickname;
    private String content;
    private String annym;

    public StickerDetailResponse(Sticker sticker) {
        this.id = sticker.getId();
        this.image = sticker.getImage();
        this.location = sticker.getLocation();
        this.nickname = sticker.getNickname();
        this.content = sticker.getContent();
        this.annym = sticker.getAnnym();
    }
}
