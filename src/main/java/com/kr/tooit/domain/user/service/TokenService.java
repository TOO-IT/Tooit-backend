package com.kr.tooit.domain.user.service;

import com.kr.tooit.domain.user.domain.User;
import com.kr.tooit.global.config.jwt.TokenProvider;
import com.kr.tooit.global.error.CustomException;
import com.kr.tooit.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;

    private final static String TOKEN_PREFIX = "Bearer ";

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }

    // Bearer를 제외한 토큰 반환
    public Long getUserId(String authorizationHeader) {
        String token = "";
        if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            token = authorizationHeader.substring(TOKEN_PREFIX.length());
        }

        if(!token.isEmpty()) {
            return tokenProvider.getUserId(token);
        }
        return null;
    }
}
