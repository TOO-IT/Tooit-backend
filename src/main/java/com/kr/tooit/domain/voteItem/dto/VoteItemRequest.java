package com.kr.tooit.domain.voteItem.dto;

import com.kr.tooit.domain.vote.domain.Vote;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteItemRequest {

  //  private MultipartFile image;
    private int stickerCount;
    private String name;
    private String content;
    private Vote vote;


//    public VoteItem toEntity() {
//        return VoteItem.builder()
//                .image(image)
//                .stickerCount(stickerCount)
//                .name(name)
//                .content(content)
//                .build();
//    }
}
