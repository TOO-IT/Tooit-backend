package com.kr.tooit.domain.review.service;

import com.kr.tooit.domain.review.domain.Review;
import com.kr.tooit.domain.review.dto.ReviewSaveRequest;
import com.kr.tooit.domain.vote.domain.Vote;
import com.kr.tooit.domain.vote.service.VoteRepository;
import com.kr.tooit.global.error.CustomException;
import com.kr.tooit.global.error.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final VoteRepository voteRepository;

    /**
     * 리뷰 작성하기
     * @param request
     * @return
     */
    @Transactional
    public boolean save(ReviewSaveRequest request) {
        if(request.getVoteId() == null) {
            new NullPointerException("투표 게시글 아이디가 NULL입니다.");
        }

        Vote findVote = voteRepository.findById(request.getVoteId())
                .orElseThrow(() -> new CustomException(ErrorCode.VOTE_NOT_FOUND));

        if(findVote.getReview() != null) {
            throw new CustomException(ErrorCode.HAS_REVIEW);
        }

        Review review = Review.builder()
                .content(request.getContent()).build();

        Review savedReview = reviewRepository.save(review);

        findVote.updateReview(savedReview);

        return true;
    }
}
