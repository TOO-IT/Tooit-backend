package com.kr.tooit.domain.user.service;


import com.kr.tooit.domain.user.dto.UserInfoDto;
import com.kr.tooit.domain.user.domain.User;
import com.kr.tooit.global.error.CustomException;
import com.kr.tooit.global.error.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
     *
     * @param refreshToken
     * @return
     */
    public UserInfoDto getUserInfo(String refreshToken) {
        Optional<User> findUser = userRepository.findByRefreshTokenWithUser(refreshToken);

        if (findUser.isEmpty()) {
            return null;
        }

        UserInfoDto userInfo = new UserInfoDto(findUser.get().getId(), findUser.get().getEmail(), findUser.get().getNickname());

        return userInfo;
    }

    /**
     * 사용자 닉네임 수정
     * @param request
     * @return UserInfoDto
     */
    @Transactional
    public UserInfoDto updateNickname(UserInfoDto request) {
        Optional<User> findUser = userRepository.findById(request.getId());

        String newNickname = request.getNickname();
        if(findUser.isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        if(newNickname.length() > 15 || newNickname.isEmpty()) {
            throw new CustomException(ErrorCode.BAD_NICKNAME);
        }

        if (newNickname == findUser.get().getNickname()) {
            return request;
        }

        // 닉네임 중복체크
        boolean flag = dupCheckNickname(request.getNickname());

        if(flag) {
            throw new CustomException(ErrorCode.HAS_NICKNAME);
        }

        findUser.get().updateNickname(newNickname);

        UserInfoDto updateUserInfo = new UserInfoDto(findUser.get().getId(),
            findUser.get().getEmail(), findUser.get().getNickname());
        return updateUserInfo;
    }



    /**
     * 사용자 닉네임 중복체크
     * @param nickname
     * @return boolean
     */
    public boolean dupCheckNickname(String nickname) {
        Optional<User> findUser = userRepository.findByNickname(nickname);

        if (findUser.isEmpty()) return false;

        return true;
    }

    /**
     * 현재 로그인한 사용자 정보 가져오기
     * @return
     */
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}