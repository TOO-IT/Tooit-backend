package com.kr.tooit.domain.vote.controller;

import com.kr.tooit.domain.vote.dto.VoteListResponse;
import com.kr.tooit.domain.vote.dto.VoteSaveRequest;
import com.kr.tooit.domain.vote.dto.VoteDetailResponse;
import com.kr.tooit.domain.vote.dto.VoteUpdateRequest;
import com.kr.tooit.domain.vote.service.VoteService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
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
     * @param order (null : 최신순, popularity : 인기순)
     * @return ResponseEntity<List<VoteListResponse>>
     */
    @GetMapping("")
    public ResponseEntity<List<VoteListResponse>> getList(@RequestParam(value = "order", required = false) String order) {
        List<VoteListResponse> list = voteService.getList(order);
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

    @PutMapping("/{id}")
    public ResponseEntity<VoteDetailResponse> update(@RequestBody @NotNull VoteUpdateRequest request,
                                                     @PathVariable("id") Long id) {
        VoteDetailResponse res = voteService.update(id, request);

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        voteService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/deadline/{id}")
    public ResponseEntity deadline(@PathVariable("id") Long id) {
        voteService.deadline(id);
        return ResponseEntity.ok(id);
    }
}
