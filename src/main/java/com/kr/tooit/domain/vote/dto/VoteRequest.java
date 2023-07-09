package com.kr.tooit.domain.vote.dto;

import com.kr.tooit.domain.user.domain.User;
import com.kr.tooit.domain.vote.domain.Vote;
import com.kr.tooit.domain.voteItem.domain.VoteItem;
import com.kr.tooit.domain.voteItem.domain.VoteItemRequest;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
public class VoteRequest {

    private String title;
    private String content;
    private String startDate;
    private String endDate;
    private String shareTarget;
    private int dDay;
    private List<VoteItemRequest> items;

    private User user;


//    @Builder
//    public VoteRequest(String title, String content, LocalDateTime startDate, LocalDateTime endDate
//                    ,String shareTarget, List<VoteItem> items) {
//
//        this.title = title;
//        this.content = content;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.shareTarget = shareTarget;
//        this.items = items;
//
//    }

    public Vote toEntity() {

        return Vote.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .shareTarget(shareTarget)
                .dDay(getDDay(startDate, endDate))
                .items(items.stream().map(VoteItemRequest::toEntity).collect(Collectors.toList()))
                .user(user)
                .build();
    }



    public int getDDay(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimeStart = LocalDateTime.parse(startDate, formatter);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(endDate, formatter);

        return (int) Duration.between(dateTimeStart, dateTimeEnd).toDays();
    }
}
