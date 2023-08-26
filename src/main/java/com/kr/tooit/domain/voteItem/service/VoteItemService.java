package com.kr.tooit.domain.voteItem.service;

import java.io.IOException;
import java.util.UUID;

import com.kr.tooit.domain.vote.service.VoteService;
import com.kr.tooit.global.config.util.FileService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.kr.tooit.domain.voteItem.domain.VoteImage;
import com.kr.tooit.domain.voteItem.domain.VoteItem;
import com.kr.tooit.domain.voteItem.repository.VoteItemRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class VoteItemService {

	private final VoteItemRepository voteItemRepository;
	private final VoteService voteService;
	private final FileService fileService;

	public String saveVoteItem(VoteImage voteImage) throws IOException {
		String imageUrl = fileService.uploadFile(voteImage.getImageFile());

		VoteItem voteItem = VoteItem.builder()
			.image(imageUrl)
			.stickerCount(0)
			.name("test")
			.content("test_content")
			.build();

		voteItemRepository.save(voteItem);
		return imageUrl;
	}

	public VoteItem findVoteItem(Long voteItemId) {
		return voteItemRepository.findById(voteItemId).orElse(null);
	}

	@Transactional
	public void updateStickerCount(VoteItem voteItem) {
		voteItemRepository.updateStickerCount(voteItem.getId());

		// Vote voteCount update
		System.out.println("vote Id : " + voteItem.getVote().getId());
		voteService.updateVoteCount(voteItem.getVote().getId());
	}
}