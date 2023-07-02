package com.kr.tooit.domain.voteItem.image;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.kr.tooit.global.config.AppConfig;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString
public class ImageBase {
	private String filePath;
	private String fileName;

	public String getURL() {
		return AppConfig.getDefaultImageUploadURL() + filePath + fileName;
	}
}
