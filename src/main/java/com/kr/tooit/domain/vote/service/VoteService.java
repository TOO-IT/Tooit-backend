package com.kr.tooit.domain.vote.service;


import com.kr.tooit.domain.user.domain.User;
import com.kr.tooit.domain.user.service.UserService;
import com.kr.tooit.domain.vote.domain.Vote;
import com.kr.tooit.domain.vote.dto.VoteListResponse;
import com.kr.tooit.domain.vote.dto.VoteRequest;
import com.kr.tooit.domain.vote.dto.VoteResponse;
import com.kr.tooit.domain.vote.dto.VoteUpdateRequest;
import com.kr.tooit.domain.voteItem.domain.VoteItem;
import com.kr.tooit.domain.voteItem.domain.repository.VoteItemRepository;
import com.kr.tooit.global.error.CustomException;
import com.kr.tooit.global.error.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    private final VoteItemRepository voteItemRepository;
    private final UserService userService;

    public List<VoteListResponse> getList(String order) {
        List<Vote> findList = null;

        if(order == null) {
            findList = voteRepository.findAll();
        }
        else {
            if(order.equals("popularity"))
            findList = voteRepository.findAll(Sort.by(Sort.Direction.DESC, "voteCount"));
        }

        List<VoteListResponse> list = findList.stream()
                .map(Vote::toEntity)
                .collect(Collectors.toList());
        return list;
    }



    @Transactional
    public VoteResponse save(VoteRequest request) {
        User user = userService.getCurrentUser();

        if(user == null) new CustomException(ErrorCode.USER_NOT_FOUND);

        request.setUser(user);
        Vote vote = request.toEntity();

        List<VoteItem> items = vote.getItems();

        Vote entity = voteRepository.save(vote);
        items.stream().forEach(i -> i.setVote(entity));

        voteItemRepository.saveAll(items);

        VoteResponse response = new VoteResponse(entity);

        return response;
    }

    @Transactional
    public VoteResponse update(Long id, VoteUpdateRequest request) {
        Vote findVote = voteRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.VOTE_NOT_FOUND));

        findVote.update(request.getContent(), request.getEndDate());

        VoteResponse response = new VoteResponse(findVote);

        return response;
    }
}
