package com.kr.tooit.domain.sticker.service;

import com.kr.tooit.domain.sticker.domain.Sticker;
import com.kr.tooit.domain.sticker.dto.StickerDetailResponse;
import com.kr.tooit.domain.sticker.dto.StickerIdListRequest;
import com.kr.tooit.domain.sticker.dto.StickerSaveRequest;
import com.kr.tooit.domain.sticker.dto.StickerUpdateRequest;
import com.kr.tooit.domain.user.domain.NonUsers;
import com.kr.tooit.domain.user.domain.User;
import com.kr.tooit.domain.user.service.NonUserService;
import com.kr.tooit.domain.user.service.UserService;
import com.kr.tooit.domain.voteItem.domain.VoteItem;
import com.kr.tooit.domain.voteItem.service.VoteItemService;
import com.kr.tooit.global.config.util.FileService;
import com.kr.tooit.global.error.CustomException;
import com.kr.tooit.global.error.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StickerService {
    private final StickerRepository stickerRepository;
    private final UserService userService;
    private final NonUserService nonUserService;
    private final VoteItemService voteItemService;
    private final FileService fileService;

    // Sticker 저장
    @Transactional
    public StickerDetailResponse save(StickerSaveRequest request, MultipartFile image) throws IOException {

        User user = null;

        // voteItem 객체 가져오기
        VoteItem voteItem = voteItemService.findVoteItem(request.getVoteItemId());
        if(voteItem == null) throw new CustomException(ErrorCode.VOTE_ITEM_NOT_FOUND);

        Sticker sticker = request.toEntity();

        // 비회원일 경우
        if(request.getNonUser() != null) {
            NonUsers nonUser = request.getNonUser();
            nonUserService.save(nonUser);
            Sticker findSticker = stickerRepository.findByNonUserIpAndVoteItemId(nonUser.getIp(), voteItem.getId());

            if(findSticker != null) {
                throw new CustomException(ErrorCode.HAS_STICKER);
            }

            sticker.addNonUser(nonUser); // nonUser 매핑
        }
        else { // 회원일 경우
            user = userService.getCurrentUser();

            if(user == null) throw new CustomException(ErrorCode.USER_NOT_FOUND);

            // 중복 투표 검사
            Sticker findSticker = stickerRepository.findByUserIdAndVoteItemId(user.getId(), voteItem.getId());

            if(findSticker != null) {
                throw new CustomException(ErrorCode.HAS_STICKER);
            }

            sticker.addUser(user); // user 매핑
        }

        sticker.addVoteItem(voteItem); // voteItem 매핑
        sticker.addImage(fileService.uploadFile(image)); // 스티커 이미지 저장


        Sticker entity = stickerRepository.save(sticker);

        voteItemService.updateStickerCount(voteItem); // voteItem stickerCount 증가

        StickerDetailResponse response = new StickerDetailResponse(entity);
        return response;
    }

    // Sticker 수정
    @Transactional
    public StickerDetailResponse update(Long id, StickerUpdateRequest request) {
        Sticker findSticker = stickerRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.VOTE_NOT_FOUND));
        //findSticker.update(request.getImage(), request.getLocation(), request.getNickname(), request.getContent(), request.getAnnym());
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
