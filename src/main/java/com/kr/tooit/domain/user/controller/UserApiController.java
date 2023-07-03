package com.kr.tooit.domain.user.controller;

import com.kr.tooit.domain.user.dto.UserInfoDto;
import com.kr.tooit.domain.user.dto.CreateAccessTokenRequest;
import com.kr.tooit.domain.user.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    /**
     * 로그인 되어 있는 User 정보 조회
     * @param request(refreshToken)
     * @return UserInfo
     */
    @PostMapping("/api/userInfo")
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
    @PutMapping("/api/nickname")
    public ResponseEntity<UserInfoDto> updateNickname(@RequestBody @NotNull UserInfoDto request) {
        UserInfoDto response = userService.updateNickname(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
