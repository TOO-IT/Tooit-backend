package com.kr.tooit.domain.voteItem.image.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ImageDTO {
	private String uploadFileName;
	private String uploadFilePath;
	private String uploadFileUrl;
}
