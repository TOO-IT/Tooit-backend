package com.kr.tooit.domain.sticker.dto;

import com.kr.tooit.domain.sticker.domain.Sticker;
import com.kr.tooit.domain.user.domain.NonUsers;
import lombok.Data;

@Data
public class StickerSaveRequest {
    private String locationX;
    private String locationY;
    private String nickname;
    private String content;
    private Long voteId;
    private Long voteItemId;
    private NonUsers nonUser;


    public Sticker toEntity() {
        return Sticker.builder()
                .locationX(locationX)
                .locationY(locationY)
                .nickname(nickname)
                .content(content)
                .voteId(voteId)
                .build();
    }
}


