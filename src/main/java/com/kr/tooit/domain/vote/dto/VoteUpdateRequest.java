package com.kr.tooit.domain.vote.dto;

import lombok.Data;

@Data
public class VoteUpdateRequest {
    private String content;
    private String endDate;
}
