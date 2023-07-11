package com.kr.tooit.domain.vote.dto;

import com.kr.tooit.domain.vote.domain.Vote;
import com.kr.tooit.domain.voteItem.domain.VoteItemResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class VoteDetailResponse {

    private Long id;
    private String title;
    private String content;
    private String startDate;
    private String endDate;
    private int dDay;
    private String createDate;
    private Long userId;

    private String nickname;
    private List<VoteItemResponse> items;




    public VoteDetailResponse(Vote entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.dDay = entity.getDDay();
        this.createDate = entity.getCreatedDate();
        this.userId = entity.getUser().getId();
        this.nickname = entity.getUser().getNickname();
        this.items = entity.getItems().stream().map(VoteItemResponse::new).collect(Collectors.toList());
    }
}
