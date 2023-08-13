package com.kr.tooit.domain.sticker.service;

import com.kr.tooit.domain.sticker.domain.Sticker;
import com.kr.tooit.domain.sticker.dto.StickerDetailResponse;
import com.kr.tooit.domain.sticker.dto.StickerIdListRequest;
import com.kr.tooit.domain.sticker.dto.StickerSaveRequest;
import com.kr.tooit.domain.sticker.dto.StickerUpdateRequest;
import com.kr.tooit.domain.user.domain.User;
import com.kr.tooit.domain.user.service.UserService;
import com.kr.tooit.domain.vote.domain.Vote;
import com.kr.tooit.domain.vote.service.VoteRepositoryPage;
import com.kr.tooit.domain.voteItem.domain.VoteItem;
import com.kr.tooit.domain.voteItem.service.VoteItemService;
import com.kr.tooit.global.error.CustomException;
import com.kr.tooit.global.error.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StickerService {
    private final StickerRepository stickerRepository;
    private final UserService userService;
    private final VoteRepositoryPage voteRepositoryPage;
    private final VoteItemService voteItemService;

    // Sticker 저장
    @Transactional
    public StickerDetailResponse save(StickerSaveRequest request) throws IOException {
        User user = userService.getCurrentUser();
        if(user == null) new CustomException(ErrorCode.USER_NOT_FOUND);
        request.setUser(user);

        Sticker sticker = request.toEntity();
        Sticker entity = stickerRepository.save(sticker);

        // Vote vote = voteItemService.get
        // request.setVoteItem(voteItem);

        StickerDetailResponse response = new StickerDetailResponse(entity);
        return response;
    }

    // Sticker 수정
    @Transactional
    public StickerDetailResponse update(Long id, StickerUpdateRequest request) {
        Sticker findSticker = stickerRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.VOTE_NOT_FOUND));
        findSticker.update(request.getImage(), request.getLocation(), request.getNickname(), request.getContent(), request.getAnnym());
        StickerDetailResponse response = new StickerDetailResponse(findSticker);
        return response;
    }

    // Sticker 삭제 -- 미사용? 기능 확인 필요
    @Transactional
    public void delete(StickerIdListRequest list) {
        /*
        List<Long> ids = list.getId();

        for(Long id : ids) {
            Optional<Sticker> findVote = stickerRepository.findById(id);
            findVote.get().deleteUpdate();
        }
         */
    }
}
