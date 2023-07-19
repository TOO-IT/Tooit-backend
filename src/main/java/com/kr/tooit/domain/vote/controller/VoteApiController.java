package com.kr.tooit.domain.vote.controller;

import com.kr.tooit.domain.vote.dto.*;
import com.kr.tooit.domain.vote.service.VoteService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
//    @PostMapping("/write")
//    public ResponseEntity<VoteDetailResponse> save(@RequestBody @NotNull VoteSaveRequest request) {
//        //ResponseEntity<VoteResponse> result = voteService.save(request);
//        VoteDetailResponse res = voteService.save(request);
//        return ResponseEntity.ok(res);
//    }
    @PostMapping("/write")
    public ResponseEntity<VoteDetailResponse> save(@RequestPart(value = "vote", required = true) VoteSaveRequest request,
                                                   @RequestPart(value = "files", required = false) List<MultipartFile> multipartFile) throws IOException {
        //ResponseEntity<VoteResponse> result = voteService.save(request);
        VoteDetailResponse res = voteService.save(multipartFile, request);
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
     * @param list
     * @return
     */
    @DeleteMapping("/")
    public ResponseEntity delete(@RequestBody VoteIdListRequest list) {
        voteService.delete(list);
        return ResponseEntity.ok(list);
    }

    /**
     * Vote 게시글 마감 처리
     * @param list
     * @return
     */
    @PutMapping("/deadline")
    public ResponseEntity deadline(@RequestBody VoteIdListRequest list) {
        voteService.deadline(list);
        return ResponseEntity.ok(list);
    }
}
