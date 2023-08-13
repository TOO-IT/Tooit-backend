package com.kr.tooit.domain.sticker.domain;

import com.kr.tooit.domain.user.domain.User;
import com.kr.tooit.domain.vote.domain.Vote;
import com.kr.tooit.domain.voteItem.domain.VoteItem;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sticker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STICKER_ID")
    private Long id; // 번호

    @Column(nullable = false)
    private String image; // 이미지

    @Column(nullable = false)
    private String location; // 위치

    private String nickname; // 닉네임

    private String content; // 설명

    private String annym; // 익명여부 ('Y'/'N')

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "VOTE_ID")
    private Vote vote;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private VoteItem voteItem;


    @Builder
    public Sticker (String image, String location, String nickname, String content, String annym, User user, VoteItem voteItem) {
        this.image = image;
        this.location = location;
        this.nickname = nickname;
        this.content = content;
        this.annym = annym;
        this.user = user;
        this.voteItem = voteItem;
    }

    public void update(String image, String location, String nickname, String content, String annym) {
        this.image = image;
        this.location = location;
        this.nickname = nickname;
        this.content = content;
        this.annym = annym;
    }

    @PrePersist
    void onPrePersist() {
        this.annym = "N";
    }

    @PreUpdate
    void onPreUpdate() {
    }

}
