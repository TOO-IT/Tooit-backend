package com.kr.tooit.domain.user.service;

import com.kr.tooit.domain.user.domain.NonUsers;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NonUserService {

    private final NonUserRepository nonUserRepository;

    @Transactional
    public void save(NonUsers nonUser) {
        nonUserRepository.save(nonUser);
    }

}
