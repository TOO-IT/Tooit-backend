package com.kr.tooit.domain.voteItem.image.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.kr.tooit.domain.voteItem.image.service.ImageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/upload")
public class ImageController {
	private final ImageService imageService;

	@GetMapping("/sample")
	public String getUpload() {
		return "upload";
	}

	@PostMapping("/sample")
	public ResponseEntity<Object> uploadImagesSample(
		@RequestPart(value = "files") List<MultipartFile> multipartFiles) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(imageService.uploadImagesSample(multipartFiles));
	}

	@PostMapping("/vote")
	public ResponseEntity<Object> uploadImageVote(
		@RequestPart(value = "files") List<MultipartFile> multipartFiles) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(imageService.uploadImageVote(null, multipartFiles));
	}
}
