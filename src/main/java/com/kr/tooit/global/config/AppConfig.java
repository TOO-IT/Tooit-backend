package com.kr.tooit.global.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
public class AppConfig {
	@Getter
	private static ApplicationContext context;
	@Getter
	private static String defaultImageUploadURL;

	@Autowired
	public void setContext(ApplicationContext context) {
		AppConfig.context = context;
	}

	@Value("${custom.image.upload-dir}")
	public void setDefaultImageUploadURL(String defaultImageUploadURL) {
		AppConfig.defaultImageUploadURL = defaultImageUploadURL;
	}
}
