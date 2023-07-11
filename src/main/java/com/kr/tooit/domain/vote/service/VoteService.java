package com.kr.tooit.domain.vote.service;


import com.kr.tooit.domain.user.domain.User;
import com.kr.tooit.domain.user.service.UserService;
import com.kr.tooit.domain.vote.domain.Vote;
import com.kr.tooit.domain.vote.dto.VoteListResponse;
import com.kr.tooit.domain.vote.dto.VoteSaveRequest;
import com.kr.tooit.domain.vote.dto.VoteDetailResponse;
import com.kr.tooit.domain.vote.dto.VoteUpdateRequest;
import com.kr.tooit.domain.voteItem.domain.VoteItem;
import com.kr.tooit.domain.voteItem.domain.repository.VoteItemRepository;
import com.kr.tooit.global.error.CustomException;
import com.kr.tooit.global.error.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    private final VoteItemRepository voteItemRepository;
    private final UserService userService;


    public List<VoteListResponse> getList(String order) {
        List<Vote> findList = null;

        String shareTarget = "A";
        String deleteStatus = "N";
        String deadlineStatus = "N";

        Specification<Vote> spec = Specification.where(VoteSpecification.equalsDeadlineStatus(deadlineStatus))
                .and(VoteSpecification.equalsDeleteStatus(deleteStatus)
                        .and(VoteSpecification.equalsShareTarget(shareTarget)));

        if(order == null) {
            findList = voteRepository.findAll(spec);
        }
        else {
            if(order.equals("popularity"))
                findList = voteRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "voteCount"));
        }

        List<VoteListResponse> list = findList.stream()
                .map(Vote::toEntity)
                .collect(Collectors.toList());
        return list;
    }



    @Transactional
    public VoteDetailResponse save(VoteSaveRequest request) {
        User user = userService.getCurrentUser();

        if(user == null) new CustomException(ErrorCode.USER_NOT_FOUND);
        request.setUser(user);
        Vote vote = request.toEntity();
        List<VoteItem> items = request.getItems().stream().map(voteItemRequest -> voteItemRequest.toEntity()).collect(Collectors.toList());
        items.stream().forEach(entity -> entity.setVote(vote));
        items.stream().forEach(entity-> vote.addItem(entity));
        Vote entity = voteRepository.save(vote);

        VoteDetailResponse response = new VoteDetailResponse(entity);
        return response;
    }

    @Transactional
    public VoteDetailResponse update(Long id, VoteUpdateRequest request) {
        Vote findVote = voteRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.VOTE_NOT_FOUND));

        findVote.update(request.getContent(), request.getEndDate());

        VoteDetailResponse response = new VoteDetailResponse(findVote);

        return response;
    }

    @Transactional
    public void delete(Long id) {

        Vote findVote = voteRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.VOTE_NOT_FOUND));

        findVote.deleteUpdate();
    }

    @Transactional
    public void deadline(Long id) {
        Vote findVote = voteRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.VOTE_NOT_FOUND));

        findVote.deadlineUpdate();
    }
}
