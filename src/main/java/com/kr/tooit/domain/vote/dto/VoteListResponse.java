package com.kr.tooit.domain.vote.dto;

import com.kr.tooit.domain.vote.domain.Vote;
import com.kr.tooit.domain.voteItem.dto.VoteItemResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteListResponse {

    private Long id;
    private String title;
    private String content;
    private int dDay;
    private String endDate;
    private List<VoteItemResponse> items = new ArrayList<>();

    private int voteCount;

    public VoteListResponse(Vote entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.dDay = entity.getDDay();
        this.endDate = entity.getEndDate();
        this.items = entity.getItems().stream().map(item -> new VoteItemResponse(item)).collect(Collectors.toList());
        this.voteCount = entity.getVoteCount();
    }
}
