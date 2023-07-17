package com.kr.tooit.domain.review.controller;

import com.kr.tooit.domain.review.dto.ReviewSaveRequest;
import com.kr.tooit.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tooit/myPage/review")
public class ReviewApiController {

    private final ReviewService reviewService;

    @PostMapping("/")
    public ResponseEntity save(@RequestBody ReviewSaveRequest request) {
        boolean result = reviewService.save(request);
        return ResponseEntity.ok(result);
    }
}
