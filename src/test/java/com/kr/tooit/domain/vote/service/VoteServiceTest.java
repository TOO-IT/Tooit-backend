package com.kr.tooit.domain.vote.service;

import com.kr.tooit.domain.sticker.domain.Sticker;
import com.kr.tooit.domain.sticker.service.StickerRepository;
import com.kr.tooit.domain.sticker.service.StickerService;
import com.kr.tooit.domain.user.domain.RefreshToken;
import com.kr.tooit.domain.user.domain.Role;
import com.kr.tooit.domain.user.domain.User;
import com.kr.tooit.domain.user.dto.CreateAccessTokenRequest;
import com.kr.tooit.domain.user.service.RefreshTokenRepository;
import com.kr.tooit.domain.user.service.UserRepository;
import com.kr.tooit.domain.user.service.UserService;
import com.kr.tooit.domain.vote.domain.Vote;
import com.kr.tooit.domain.voteItem.domain.VoteItem;
import com.kr.tooit.domain.voteItem.dto.VoteItemResponse;
import com.kr.tooit.domain.voteItem.repository.VoteItemRepository;
import com.kr.tooit.domain.voteItem.service.VoteItemService;
import com.kr.tooit.global.config.jwt.JwtFactory;
import com.kr.tooit.global.config.jwt.JwtProperties;
import com.kr.tooit.global.config.jwt.TokenProvider;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


@SpringBootTest
@Slf4j
class VoteServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    StickerRepository stickerRepository;

    @Autowired
    VoteItemRepository voteItemRepository;

    @Autowired
    VoteItemService voteItemService;

    @DisplayName("테스트 데이터 추가")
    @Test
    public void testData() {

        /* user 테이블에 user 데이터가 두 개는 있어야 함 */
        // user1 --> vote 생성
        // user2 --> 투표

        // 맨 마지막 user 정보 가져오기
        List<User> allUsers = userRepository.findAll();
        User user = allUsers.get(allUsers.size() -1);


        // 100개의 Vote 객체 생성
        for(int i = 0; i < 100; i++) {

            // vote 객체 생성
            Vote savedVote = voteRepository.save(Vote.builder()
                    .user(user)
                    .title(i + "번째 테스트 제목입니다.")
                    .content(i + "번째 테스트 내용입니다.")
                    .shareTarget("A")
                    .startDate("2023-09-05 12:00")
                    .endDate("2023-09-20 13:00")
                    .dDay(getDDay("2023-09-05 12:00", "2023-09-20 13:00"))
                    .thumbnail("https://tooit.s3.ap-northeast-2.amazonaws.com/voteImage/a21607e0-3dfa-4afc-bd1a-7bd8e2b3e3bf_thumbnail.jpeg")
                    .build());

            // VoteItem 객체 생성
            VoteItem savedItem = null;
            for(int j = 1; j <= 4; j++) {

                int randomNumber = (int) (Math.random() * 4) * 1;

                // voteItem 객체 생성
                VoteItem newItem = VoteItem.builder()
                        .stickerCount(0)
                        .image("https://tooit-vote.s3.ap-northeast-2.amazonaws.com/voteImage/67808d55-fcfd-46b2-96cc-a094eafabceb_%E1%84%8E%E1%85%AE%E1%86%AB%E1%84%89%E1%85%B5%E1%86%A81.png")
                        .content(j + "번째 voteItem 테스트 ")
                        .name(j + "번째 item")
                        .build();

                newItem.setVote(savedVote);

                savedItem = voteItemRepository.save(newItem);

                if(j == randomNumber) break;
            }

            // Sticker 객체 생성
            Sticker savedSticker = stickerRepository.save(Sticker.builder()
                    .image("https://tooit-vote.s3.ap-northeast-2.amazonaws.com/voteImage/cfcadd10-f8bd-473f-afe3-013803c8b89f_thumbnail.jpeg")
                    .voteItem(savedItem)
                    .locationX(i + ".4")
                    .locationY(i + 1 + ".7")
                    .nickname("스티커 테스트")
                    .user(allUsers.get(allUsers.size() - 2))
                    .voteId(savedItem.getVote().getId())
                    .build());

            voteItemService.updateStickerCount(savedItem);
        }
    }

    public int getDDay(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimeStart = LocalDateTime.parse(startDate, formatter);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(endDate, formatter);

        return (int) Duration.between(dateTimeStart, dateTimeEnd).toDays();
    }
}