package com.kr.tooit.domain.sticker.dto;

import com.kr.tooit.domain.sticker.domain.Sticker;
import com.kr.tooit.domain.user.domain.User;
import com.kr.tooit.domain.voteItem.domain.VoteItem;
import lombok.Data;

@Data
public class StickerSaveRequest {
    private Long id;
    private String image;
    private String location;
    private String nickname;
    private String content;
    private String annym;

    private User user;
    private VoteItem voteItem;

    public Sticker toEntity() {
        return Sticker.builder().image(image).location(location).nickname(nickname).content(content).annym(annym).user(user).voteItem(voteItem).build();
    }
}


