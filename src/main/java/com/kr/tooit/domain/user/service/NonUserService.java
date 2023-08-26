package com.kr.tooit.domain.user.service;

import com.kr.tooit.domain.user.domain.NonUsers;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NonUserService {

    private final NonUserRepository nonUserRepository;

    @Transactional
    public void save(NonUsers nonUser) {
        Optional<NonUsers> findNonUser = nonUserRepository.findById(nonUser.getIp());

        if(findNonUser.isEmpty()) {
            nonUserRepository.save(nonUser);
        }
    }

}
