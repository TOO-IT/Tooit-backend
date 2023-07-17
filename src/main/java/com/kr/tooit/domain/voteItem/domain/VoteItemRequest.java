package com.kr.tooit.domain.voteItem.domain;

import com.kr.tooit.domain.vote.domain.Vote;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteItemRequest {

    private String image;
    private int stickerCount;
    private String name;
    private String content;
    private Vote vote;


    public VoteItem toEntity() {
        return VoteItem.builder()
                .image(image)
                .stickerCount(stickerCount)
                .name(name)
                .content(content)
               // .vote(vote)
                .build();
    }
}
