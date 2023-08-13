package com.kr.tooit.domain.vote.dto;

import com.kr.tooit.domain.vote.domain.Vote;
import com.kr.tooit.domain.voteItem.domain.VoteItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteMyVoteListResponse {

    private Long id;
    private String title;
    private String content;
    private String numberPersons;
    private String dDay;
    private String topItem;
    private boolean reviewFlag;

    private String thumbnail;

    public VoteMyVoteListResponse(Vote entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.numberPersons = entity.getVoteCount() + "명 참여";
        this.dDay = entity.getDDay() == 0 ? "D-day" : "D-" + entity.getDDay();
        this.topItem = getTopItem(entity.getItems());
        this.reviewFlag = entity.getReview() == null ? false : true;
        this.thumbnail = entity.getThumbnail();
    }

    public String getTopItem(List<VoteItem> items) {
        int max = items.get(0).getStickerCount();
        String result = null;

        for(int i = 1; i < items.size(); i++) {
            if(max < items.get(i).getStickerCount()) {
                max = items.get(i).getStickerCount();
                result = items.get(i).getName();
            }
        }

        if(result == null) result = items.get(0).getName();

        return result;
    }
}
