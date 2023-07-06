package com.kr.tooit.domain.voteItem.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteImage {
	private MultipartFile imageFile;

	public VoteImage(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
}
