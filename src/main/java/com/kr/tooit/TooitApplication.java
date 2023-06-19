package com.kr.tooit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TooitApplication {

	public static void main(String[] args) {
		SpringApplication.run(TooitApplication.class, args);
	}

}
