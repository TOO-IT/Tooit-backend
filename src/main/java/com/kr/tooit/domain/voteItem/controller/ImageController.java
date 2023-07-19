package com.kr.tooit.domain.voteItem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kr.tooit.domain.user.service.UserService;
import com.kr.tooit.domain.voteItem.domain.VoteImage;
import com.kr.tooit.domain.voteItem.service.VoteItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tooit/upload")
public class ImageController {
	private final VoteItemService voteItemService;
	private final UserService userService;

	@GetMapping("/voteImage")
	public String getUpload() {
		return "upload";
	}

	@PostMapping("/voteImage")
	public ResponseEntity<Object> uploadFile(@RequestParam("image") MultipartFile image) {
		try {
			VoteImage voteImage = new VoteImage(image);
			String imageUrl = voteItemService.saveVoteItem(voteImage);
			return ResponseEntity.ok(imageUrl);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
