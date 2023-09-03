package com.kr.tooit.domain.sticker.controller;

import com.kr.tooit.domain.sticker.dto.*;
import com.kr.tooit.domain.sticker.service.StickerService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tooit/sticker")
public class StickerApiController {
    private final StickerService stickerService;

    @PostMapping("")
    public ResponseEntity<StickerDetailResponse> save(@RequestPart(value = "sticker", required = true) StickerSaveRequest request,
                                                      @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        StickerDetailResponse res = stickerService.save(request, image);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/")
    public ResponseEntity<StickerDetailResponse> reVote(@RequestBody @NotNull StickerUpdateRequest request,
                                                     @PathVariable("id") Long id) {
        StickerDetailResponse res = stickerService.update(id, request);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("")
    public ResponseEntity delete(@RequestBody StickerDeleteRequest request) {
        stickerService.delete(request);
        return ResponseEntity.ok("");
    }
}
