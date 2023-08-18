package com.dnd.bbok.domain.friend.service;

import com.dnd.bbok.domain.friend.entity.BbokCharacter;
import com.dnd.bbok.infra.s3.service.S3Downloader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * S3에 저장된 아이콘을 불러오는 서비스.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class IconService {

  private final S3Downloader s3Downloader;

  /**
   * 하나의 Icon url만 반환하는 로직
   */
  public String getIconUrl(BbokCharacter bbok) {
    return s3Downloader.getIconUrl(bbok.getIconFile());
  }

}
