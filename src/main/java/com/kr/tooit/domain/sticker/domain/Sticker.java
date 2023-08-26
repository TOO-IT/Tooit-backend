package com.kr.tooit.domain.sticker.domain;

import com.kr.tooit.domain.user.domain.NonUsers;
import com.kr.tooit.domain.user.domain.User;
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
    private String locationX; // X 위치

    @Column(nullable = false)
    private String locationY; // Y 위치

    private String nickname; // 닉네임

    private String content; // 설명


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "NON_USER_IP")
    private NonUsers nonUser;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private VoteItem voteItem;

    @Column(name = "VOTE_ID")
    private Long voteId;


    @Builder
    public Sticker (String image, String locationX, String locationY, String nickname, String content, User user, VoteItem voteItem, Long voteId) {
        this.image = image;
        this.locationX = locationX;
        this.locationY = locationY;
        this.nickname = nickname;
        this.content = content;
        this.user = user;
        this.voteItem = voteItem;
        this.voteId = voteId;
    }


    public void update(String image, String locationX, String locationY, String nickname, String content) {
        this.image = image;
        this.locationX = locationX;
        this.locationY = locationY;
        this.nickname = nickname;
        this.content = content;
    }

    public void addUser(User user) {
        if(this.user == null) this.user = user;
    }

    public void addNonUser(NonUsers anonymous) {
        if(this.nonUser == null) this.nonUser = anonymous;
    }

    public void addVoteItem(VoteItem voteItem) {
        if(this.voteItem == null) {
            this.voteItem = voteItem;
        }
    }

    public void addImage(String image) {
        if(this.image == null) {
            this.image = image;
        }
    }
}
