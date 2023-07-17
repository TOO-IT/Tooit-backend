package com.kr.tooit.domain.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@Builder
public class VoteSliceResponse {

    private boolean nextPage;
    private List<?> list;

    public VoteSliceResponse(boolean nextPage, List<?> list) {
        this.nextPage = nextPage;
        this.list = list;
    }
}
