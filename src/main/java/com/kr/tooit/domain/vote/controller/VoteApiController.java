package com.kr.tooit.domain.vote.controller;

import com.kr.tooit.domain.vote.dto.*;
import com.kr.tooit.domain.vote.service.VoteService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tooit/vote")
public class VoteApiController {

    private final VoteService voteService;

    /**
     * Vote List 조회
     *
     * @param order (null : 최신순, popularity : 인기순)
     * @return ResponseEntity<List < VoteListResponse>>
     */
    @GetMapping("")
    public ResponseEntity<VoteSliceResponse> getList(@RequestParam(value = "order", required = false, defaultValue = "newest") String order,
                                                                 @RequestParam(value = "lastVoteId", defaultValue = "0") Long lastVoteId,
                                                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                                                 @RequestParam(value = "size", defaultValue = "10") int size) {
        VoteSliceResponse list = voteService.getList(order, lastVoteId, size, page);
        return ResponseEntity.ok(list);
    }

    /**
     * Vote Save
     * @param request
     * @return ResponseEntity<VoteResponse>
     */
    @PostMapping("/write")
    public ResponseEntity<VoteDetailResponse> save(@RequestBody @NotNull VoteSaveRequest request) {
        //ResponseEntity<VoteResponse> result = voteService.save(request);
        VoteDetailResponse res = voteService.save(request);
        return ResponseEntity.ok(res);
    }

    /**
     * Vote 게시글 수정
     * @param request
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<VoteDetailResponse> update(@RequestBody @NotNull VoteUpdateRequest request,
                                                     @PathVariable("id") Long id) {
        VoteDetailResponse res = voteService.update(id, request);

        return ResponseEntity.ok(res);
    }

    /**
     * Vote 게시글 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        voteService.delete(id);
        return ResponseEntity.ok(id);
    }

    /**
     * Vote 게시글 마감 처리
     * @param id
     * @return
     */
    @PutMapping("/deadline/{id}")
    public ResponseEntity deadline(@PathVariable("id") Long id) {
        voteService.deadline(id);
        return ResponseEntity.ok(id);
    }
}
