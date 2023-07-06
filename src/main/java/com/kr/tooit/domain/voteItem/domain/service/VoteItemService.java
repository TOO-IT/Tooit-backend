package com.kr.tooit.domain.voteItem.domain.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.kr.tooit.domain.voteItem.domain.VoteImage;
import com.kr.tooit.domain.voteItem.domain.VoteItem;
import com.kr.tooit.domain.voteItem.domain.repository.VoteItemRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class VoteItemService {
	private static final String VOTE_IMAGE_FOLDER = "voteImage/";
	private final AmazonS3 s3Client;
	private final VoteItemRepository voteItemRepository;
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	public String uploadFile(MultipartFile multipartFile) throws IOException {

		String fileName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();

		s3Client.putObject(new PutObjectRequest(bucket, VOTE_IMAGE_FOLDER + fileName, multipartFile.getInputStream(),
			new ObjectMetadata()));

		return s3Client.getUrl(bucket, VOTE_IMAGE_FOLDER + fileName).toString();
	}

	public VoteItem saveVoteItem(VoteImage voteImage) throws IOException {
		String imageUrl = uploadFile(voteImage.getImageFile());

		VoteItem voteItem = VoteItem.builder()
			.image(imageUrl)
			.stickerCount(0)
			.name("test")
			.content("test_content")
			.build();

		return voteItemRepository.save(voteItem);
	}
}