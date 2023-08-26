package com.kr.tooit.domain.user.service;

import com.kr.tooit.domain.user.domain.NonUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonUserRepository extends JpaRepository<NonUsers, String> {

}
