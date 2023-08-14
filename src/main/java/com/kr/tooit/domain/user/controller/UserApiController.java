package com.kr.tooit.domain.user.controller;

import com.kr.tooit.domain.user.dto.UserInfoDto;
import com.kr.tooit.domain.user.dto.CreateAccessTokenRequest;
import com.kr.tooit.domain.user.dto.UserUpdateDto;
import com.kr.tooit.domain.user.service.UserService;
import com.kr.tooit.domain.vote.dto.VoteSliceResponse;
import com.kr.tooit.domain.vote.service.VoteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tooit/")
public class UserApiController {

    private final UserService userService;
    private final VoteService voteService;

    /**
     * 로그인 되어 있는 User 정보 조회
     * @param request(refreshToken)
     * @return UserInfo
     */
    @PostMapping("/userInfo")
    public ResponseEntity<UserInfoDto> getUserInfo(@RequestBody @NotNull CreateAccessTokenRequest request) {
        UserInfoDto findUserInfo = userService.getUserInfo(request.getRefreshToken());
        if(findUserInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(findUserInfo);
    }

    /**
     * 사용자 닉네임 수정
     * @param request
     * @return UserInfoDto
     */
    @PutMapping("/nickname")
    public ResponseEntity<UserInfoDto> updateNickname(@RequestBody @NotNull UserUpdateDto dto,
                                                      HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        UserInfoDto response = userService.updateNickname(dto.getNickname(), token);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     *  마이페이지 내가 작성한 게시글 조회
     * @param size
     * @param lastVoteId
     * @return
     */
    @GetMapping("/myPage/vote")
    public ResponseEntity<VoteSliceResponse> getMyVotes(@RequestParam(value = "size", defaultValue = "10") int size,
                                                        @RequestParam(value = "lastVoteId", defaultValue = "0") Long lastVoteId,
                                                        HttpServletRequest request
    ) {
        // 현재 로그인 중인 user Id 가져오기
        String token = request.getHeader("Authorization");
        VoteSliceResponse result = voteService.getMyVotes(token, size, lastVoteId);
        return ResponseEntity.ok(result);
    }
}
