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
    private Long id;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String location;

    private String nickname;

    private String content;

    private String annym;

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
    public Sticker (String image, String location, String nickname, String content, String annym) {
        this.image = image;
        this.location = location;
        this.nickname = nickname;
        this.content = content;
        this.annym = annym;
    }
}
