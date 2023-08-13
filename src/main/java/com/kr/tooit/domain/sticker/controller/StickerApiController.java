package com.kr.tooit.domain.sticker.controller;

import com.kr.tooit.domain.sticker.dto.StickerDetailResponse;
import com.kr.tooit.domain.sticker.dto.StickerIdListRequest;
import com.kr.tooit.domain.sticker.dto.StickerSaveRequest;
import com.kr.tooit.domain.sticker.dto.StickerUpdateRequest;
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

    @PostMapping("/write")
    public ResponseEntity<StickerDetailResponse> save(@RequestPart(value = "sticker", required = true) StickerSaveRequest request) throws IOException {
        StickerDetailResponse res = stickerService.save(request);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StickerDetailResponse> update(@RequestBody @NotNull StickerUpdateRequest request,
                                                     @PathVariable("id") Long id) {
        StickerDetailResponse res = stickerService.update(id, request);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/")
    public ResponseEntity delete(@RequestBody StickerIdListRequest list) {
        stickerService.delete(list);
        return ResponseEntity.ok(list);
    }
}
