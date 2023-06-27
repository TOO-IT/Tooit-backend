package com.kr.tooit.domain.user.controller;

import com.kr.tooit.domain.user.dto.CreateAccessTokenRequest;
import com.kr.tooit.domain.user.dto.CreateAccessTokenResponse;
import com.kr.tooit.domain.user.service.TokenService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@RestController
public class UserTokenController {

    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken
            (@RequestBody @NotNull CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccessToken));
    }

    @PostMapping("/api/test")
    public void testApi(@RequestBody String test ) {
        System.out.println("testApi is param : " + test);
    }
}
