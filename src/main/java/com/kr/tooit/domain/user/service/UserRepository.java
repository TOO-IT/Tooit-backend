package com.kr.tooit.domain.user.service;

import com.kr.tooit.domain.user.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 사용자 정보 조회
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long userId);

    @Query(value = "select u from User u where u.id IN (select r.userId from RefreshToken r where r.refreshToken =:token)")
    Optional<User> findByRefreshTokenWithUser(@Param("token") String refreshToken);

    Optional<User> findByNickname(String nickname);

}
