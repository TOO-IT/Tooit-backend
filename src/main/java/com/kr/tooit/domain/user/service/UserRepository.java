package com.kr.tooit.domain.user.service;

import com.kr.tooit.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 사용자 정보 조회
    Optional<User> findByEmail(String email);
   // Optional<User> findById(String userId);

}
