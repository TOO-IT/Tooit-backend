package com.kr.tooit.domain.user.domain;

import com.kr.tooit.domain.vote.domain.Vote;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(unique = true, nullable = false)
    private String email; // 가입 이메일

    @Column(nullable = false, unique = true, length = 15)  // 닉네임 중복 허용 할건지?
    private String nickname; // 닉네임

    private String image; // 프로필 사진

    @CreatedDate
    private LocalDateTime createDate; // 가입 날짜

//    @OneToMany(mappedBy = "user")
//    List<Vote> votes = new ArrayList<>();


    //OAuth2
    private String provider; // google, naver, kakao
    private String providerId; // OAuth의 key(id)

    @Builder
    public User(String email, String nickname, String image, String provider, String providerId) {
        this.email = email;
        this.nickname = nickname;
        this.image = image;
        this.provider = provider;
        this.providerId = providerId;
    }






}
