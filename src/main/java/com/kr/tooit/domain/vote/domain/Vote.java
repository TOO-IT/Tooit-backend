package com.kr.tooit.domain.vote.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kr.tooit.domain.review.domain.Review;
import com.kr.tooit.domain.user.domain.User;
import com.kr.tooit.domain.vote.dto.VoteListResponse;
import com.kr.tooit.domain.voteItem.domain.VoteItem;
import com.kr.tooit.domain.voteItem.dto.VoteItemResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VOTE_ID")
    private Long id;

    @Column(nullable = false, length = 30)
    private String title; // 투표 제목

    @Column(nullable = false, length = 50)
    private String content; // 투표 설명

    @Column(name = "START_DATE", nullable = false)
    private String startDate; // 시작 날짜

    @Column(name = "END_DATE", nullable = false)
    private String endDate; // 종료 날짜

    @Column(name = "SHARE_TARGET", nullable = false)
    private String shareTarget; // 투표 참여 대상 (A: 모든 사용자 / L: 링크 공유)

    @Column(name = "D_DAY", nullable = false)
    private int dDay; // 디데이

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false, nullable = false)
    private String createdDate; // 게시글 생성 날짜

    @LastModifiedDate
    @Column(name = "MODIFIED_DATE")
    private String modifiedDate; // 게시글 수정 날짜

    @Column(name = "DELETE_STATUS") // 게시글 삭제 여부
    @ColumnDefault("'N'")
    private String deleteStatus;

    private String deadlineStatus;

    @ColumnDefault("0")
    private int voteCount; // 총 투표 수

    @Column(name = "THUMBNAIL")
    private String thumbnail; // 게시글 썸네일

    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<VoteItem> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Review review;

    @Builder
    public Vote(String title, String content, String startDate, String endDate,
                String shareTarget, int dDay, User user) {
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.shareTarget = shareTarget;
        this.dDay = dDay;
        this.user = user;
    }

    public VoteListResponse toEntity() {
        List<VoteItemResponse> list = items.stream().map(item -> new VoteItemResponse(item)).collect(Collectors.toList());
        return new VoteListResponse(id, title, content, dDay, endDate, list, voteCount, thumbnail);
    }

    public void update(String content, String endDate) {
        this.content = content;
        this.endDate = endDate;

        this.dDay = getDDay(this.startDate, endDate);
    }

    public int getDDay(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimeStart = LocalDateTime.parse(startDate, formatter);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(endDate, formatter);

        return (int) Duration.between(dateTimeStart, dateTimeEnd).toDays();
    }

    public void deleteUpdate() {
        this.deleteStatus = "Y";
    }

    public void deadlineUpdate() {
        this.deadlineStatus = "Y";
    }

    public void updateReview(Review review) {
        this.review = review;
    }

    public void saveThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @PrePersist
    void onPrePersist() {
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.modifiedDate = createdDate;
    }

    @PreUpdate
    void onPreUpdate() {
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm"));
    }

    public void addItem(VoteItem item) {
        this.items.add(item);

        if(item.getVote() != this) {
            item.setVote(this);
        }
    }
}
