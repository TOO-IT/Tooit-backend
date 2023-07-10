package com.kr.tooit.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "ACCOUNT-001", "사용자를 찾을 수 없습니다."),
    HAS_NICKNAME(HttpStatus.BAD_REQUEST, "ACCOUNT-002", "중복된 닉네임입니다."),
    BAD_NICKNAME(HttpStatus.BAD_REQUEST, "ACCOUNT-003", "올바르지 않은 닉네임입니다. 닉네임은 1~15자 이하로 작성해주세요."),

    VOTE_NOT_FOUND(HttpStatus.NOT_FOUND, "VOTE-001", "투표 게시글을 찾을 수 없습니다.")
    ;



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
