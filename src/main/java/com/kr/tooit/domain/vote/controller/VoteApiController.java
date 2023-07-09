package com.kr.tooit.domain.vote.controller;

import com.kr.tooit.domain.vote.dto.VoteListResponse;
import com.kr.tooit.domain.vote.dto.VoteRequest;
import com.kr.tooit.domain.vote.dto.VoteResponse;
import com.kr.tooit.domain.vote.service.VoteService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tooit/vote")
public class VoteApiController {

    private final VoteService voteService;

    /**
     * Vote List 조회
     * @param order (null : 최신순, popularity : 인기순)
     * @return
     */
    @GetMapping("")
    public ResponseEntity<List<VoteListResponse>> getList(@RequestParam(value = "order", required = false) String order) {
        List<VoteListResponse> list = voteService.getList(order);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/write")
    public ResponseEntity<VoteResponse> save(@RequestBody @NotNull VoteRequest request, Principal principal) {
        //ResponseEntity<VoteResponse> result = voteService.save(request);
        VoteResponse res = voteService.save(request, principal);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
