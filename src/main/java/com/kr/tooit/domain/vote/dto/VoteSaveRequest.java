package com.kr.tooit.domain.vote.dto;

import com.kr.tooit.domain.user.domain.User;
import com.kr.tooit.domain.vote.domain.Vote;
import com.kr.tooit.domain.voteItem.dto.VoteItemRequest;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class VoteSaveRequest {

    private String title;
    private String content;
    private String startDate;
    private String endDate;
    private String shareTarget;
    private int dDay;
   // private MultipartFile thumbnail;
    private List<VoteItemRequest> items;
    private User user;


    public Vote toEntity() {
        return Vote.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .shareTarget(shareTarget)
                .dDay(getDDay(startDate, endDate))
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
