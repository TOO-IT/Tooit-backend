package com.kr.tooit.domain.sticker.dto;

import lombok.Data;

@Data
public class StickerUpdateRequest {
    private Long id;
    private String image;
    private String location;
    private String nickname;
    private String content;
    private String annym;
}