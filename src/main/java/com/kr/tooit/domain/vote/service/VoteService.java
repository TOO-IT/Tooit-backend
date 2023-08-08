package com.kr.tooit.domain.vote.service;


import com.kr.tooit.domain.user.domain.User;
import com.kr.tooit.domain.user.service.UserService;
import com.kr.tooit.domain.vote.domain.Vote;
import com.kr.tooit.domain.vote.dto.*;
import com.kr.tooit.domain.voteItem.domain.VoteItem;
import com.kr.tooit.domain.voteItem.dto.VoteItemRequest;
import com.kr.tooit.domain.voteItem.service.VoteItemService;
import com.kr.tooit.global.error.CustomException;
import com.kr.tooit.global.error.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserService userService;
    private final VoteRepositoryPage voteRepositoryPage;
    private final VoteItemService voteItemService;

    /**
     * Vote 리스트 조회 (최신순, 인기순)
     * 무한스크롤 구현
     * @param order
     * @param lastVoteId
     * @param size
     * @param page
     * @return
     */
    public VoteSliceResponse getList(String order, Long lastVoteId, int size, int page) {

        List<Vote> findList = null;
        Pageable pageable = null;

        if(order == null) {
            order = "newest";
        }

        if(order.equals("newest")) {
            pageable = PageRequest.of(0, size);
            findList = voteRepositoryPage.getSlice(lastVoteId, pageable);
        }
        else {
            if(order.equals("popularity")) {
                pageable = PageRequest.of(page, size);
                findList = voteRepositoryPage.getSliceOrderByVoteCount(pageable);
            }
        }

        List<VoteListResponse> list = findList.stream()
                .map(Vote::toEntity)
                .collect(Collectors.toList());

        Slice<VoteListResponse> voteListResponses = checkLastPage(pageable, list);

        VoteSliceResponse res = new VoteSliceResponse(voteListResponses.hasNext(), list);

        return res;
    }

    /**
     * Vote 리스트 조회 페이징 hasNext 판별
     * @param pageable
     * @param list
     * @return
     */
    private Slice<VoteListResponse> checkLastPage(Pageable pageable, List<VoteListResponse> list) {
        boolean hasNext = false;

        if(list.size() > pageable.getPageSize()) {
            hasNext = true;
            list.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(list, pageable, hasNext);
    }

    /**
     * Vote 게시글 단건 조회
     * @param id
     * @return
     */
    public VoteDetailResponse getVote(Long id) {
        Vote findVote = voteRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.VOTE_NOT_FOUND));

        return new VoteDetailResponse(findVote);
    }

    /**
     * Vote 게시글 저장
     * @param request
     * @return
     */
    @Transactional
    public VoteDetailResponse save(List<MultipartFile> multipartFile, VoteSaveRequest request) throws IOException {
        User user = userService.getCurrentUser();

        if(user == null) new CustomException(ErrorCode.USER_NOT_FOUND);
        request.setUser(user);

        List<VoteItem> items = new ArrayList<>();

        for(int i = 0; i < request.getItems().size(); i++) {
            VoteItemRequest item = request.getItems().get(i);
            VoteItem buildItem = VoteItem.builder()
                    .image(voteItemService.uploadFile(multipartFile.get(i)))
                    .stickerCount(item.getStickerCount())
                    .name(item.getName())
                    .content(item.getContent())
                    .build();

            items.add(buildItem);
        }

        Vote vote = request.toEntity();
        items.stream().forEach(entity -> entity.setVote(vote));

        Vote entity = voteRepository.save(vote);

        VoteDetailResponse response = new VoteDetailResponse(entity);
        return response;
    }

    /**
     * Vote 게시글 수정
     * @param id
     * @param request
     * @return
     */
    @Transactional
    public VoteDetailResponse update(Long id, VoteUpdateRequest request) {
        Vote findVote = voteRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.VOTE_NOT_FOUND));

        findVote.update(request.getContent(), request.getEndDate());

        VoteDetailResponse response = new VoteDetailResponse(findVote);

        return response;
    }


    /**
     * Vote 게시글 삭제
     * @param list
     */
    @Transactional
    public void delete(VoteIdListRequest list) {

        List<Long> ids = list.getId();

        for(Long id : ids) {
            Optional<Vote> findVote = voteRepository.findById(id);
            findVote.get().deleteUpdate();
        }
    }

    /**
     * Vote 게시글 마감 처리
     * @param list
     */
    @Transactional
    public void deadline(VoteIdListRequest list) {

        List<Long> ids = list.getId();

        for(Long id : ids) {
            Optional<Vote> findVote = voteRepository.findById(id);
            findVote.get().deadlineUpdate();
        }
    }

    public VoteSliceResponse getMyVotes(Long id, int size, Long lastVoteId) {
        Pageable pageable = PageRequest.of(0, size);

        List<Vote> list = voteRepositoryPage.getSliceMyVotes(id, pageable);

        List<VoteMyVoteListResponse> result = list.stream().map(entity -> new VoteMyVoteListResponse(entity)).collect(Collectors.toList());

        Slice<VoteMyVoteListResponse> voteListResponses = checkMyLastPage(pageable, result);

        VoteSliceResponse res = new VoteSliceResponse(voteListResponses.hasNext(), result);

        return res;
    }

    private Slice<VoteMyVoteListResponse> checkMyLastPage(Pageable pageable, List<VoteMyVoteListResponse> list) {
        boolean hasNext = false;

        if(list.size() > pageable.getPageSize()) {
            hasNext = true;
            list.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(list, pageable, hasNext);
    }
}
