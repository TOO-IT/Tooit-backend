package com.kr.tooit.domain.vote.domain;


import com.kr.tooit.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VOTE_ID")
    private Long id;

    @Column(nullable = false, length = 30)
    private String title; // 투표 제목

    @Column(nullable = false, length = 50)
    private String content; // 투표 설명

    /**
     * 이 컬럼은 필요 없을 수도... 일단 Budiler에서 제외
     */
    @Column(nullable = false)
    private String period; // 기간 (시작 날짜 ~ 종료 날짜)

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate; // 시작 날짜

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate; // 종료 날짜

    @Column(name = "SHARE_TARGET", nullable = false)
    private String shareTarget; // 투표 참여 대상 (A: 모든 사용자 / L: 링크 공유)

    @Column(name = "D_DAY", nullable = false)
    private String dDay; // 디데이

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate; // 게시글 생성 날짜

    @LastModifiedDate
    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate; // 게시글 수정 날짜

    @Column(name = "DELETE_STATUS") // 게시글 삭제 여부
    @ColumnDefault("N")
    private String deleteStatus;

    @Column(name = "DEADLINE_STATUS") // 게시글 투표 마감 여부
    @ColumnDefault("N")
    private String deadlineStatus;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


    @Builder
    public Vote(String title, String content, LocalDateTime startDate, LocalDateTime endDate,
                String shareTarget, String dDay) {
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.shareTarget = shareTarget;
        this.dDay = dDay;
    }
}
