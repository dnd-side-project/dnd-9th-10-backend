package com.dnd.bbok.infra.s3.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Downloader {

  private final AmazonS3 s3;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  public String getIconUrl(String iconFile) {
    GeneratePresignedUrlRequest requestUrl =
        new GeneratePresignedUrlRequest(bucket, iconFile, HttpMethod.GET);
    return s3.generatePresignedUrl(requestUrl).toString();
  }

}
