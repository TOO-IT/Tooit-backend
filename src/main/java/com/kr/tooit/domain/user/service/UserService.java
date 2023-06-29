package com.kr.tooit.domain.user.service;

import com.kr.tooit.domain.user.dto.UserInfoResponse;
import com.kr.tooit.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    /**
     * Refresh Token으로 UserInfo 조회
     * @param refreshToken
     * @return
     */
    public UserInfoResponse getUserInfo(String refreshToken) {
        Optional<User> findUser = userRepository.findByRefreshTokenWithUser(refreshToken);

        if(findUser.isEmpty()) { return null; }

        UserInfoResponse userInfo = new UserInfoResponse(findUser.get().getId(), findUser.get().getEmail(), findUser.get().getNickname());

        return userInfo;
    }
}
