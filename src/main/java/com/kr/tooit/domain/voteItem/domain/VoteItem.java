package com.kr.tooit.domain.voteItem.domain;

import com.kr.tooit.domain.sticker.domain.Sticker;
import com.kr.tooit.domain.vote.domain.Vote;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "ITEM")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VoteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    private String image;

    @Column(name = "STICKER_COUNT")
    private int stickerCount;

    private String name;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    @OneToMany(mappedBy = "voteItem")
    List<Sticker> stickers = new ArrayList<>();

    @Builder
    public VoteItem(String image, int stickerCount, String name, String content, Vote vote) {
        this.image = image;
        this.stickerCount = stickerCount;
        this.name = name;
        this.content = content;
        //this.vote = vote;
    }

    public void setVote(final Vote vote) {
        if(this.vote != null) {
            this.vote.getItems().remove(this);
        }
        this.vote = vote;

        if(!vote.getItems().contains(this)) {
            //vote.getItems().add(this);
            vote.addItem(this);
        }
    }
}
