package com.kr.tooit.global.config.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private static final String VOTE_IMAGE_FOLDER = "voteImage/";
    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFile(MultipartFile multipartFile) throws IOException {

        if(multipartFile.isEmpty() || multipartFile.getOriginalFilename().length() == 0) return "";

        String fileName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();

        // 이미지가 있는지 확인하고 Content-Type을 설정
        ObjectMetadata objectMetadata = new ObjectMetadata();
        if (multipartFile.getContentType() != null) {
            objectMetadata.setContentType(multipartFile.getContentType());
        }

        s3Client.putObject(
                new PutObjectRequest(bucket, VOTE_IMAGE_FOLDER + fileName, multipartFile.getInputStream(), objectMetadata));

        return s3Client.getUrl(bucket, VOTE_IMAGE_FOLDER + fileName).toString();
    }
}
