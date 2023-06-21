package com.kr.tooit.domain.voteItem.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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


    @Builder
    public VoteItem(String image, int stickerCount, String name, String content) {
        this.image = image;
        this.stickerCount = stickerCount;
        this.name = name;
        this.content = content;
    }
}
