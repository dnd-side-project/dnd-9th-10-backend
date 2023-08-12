package com.dnd.bbok.domain.saying.service;

import com.dnd.bbok.domain.diary.dto.response.DiarySayingDto;
import com.dnd.bbok.domain.saying.entity.Saying;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 명언 조회에 사용하는 서비스
 */
@Service
@RequiredArgsConstructor
public class SayingService {
    private final BookmarkEntityService bookmarkEntityService;
    private final SayingEntityService sayingEntityService;

    public DiarySayingDto getRandomSaying(UUID memberId) {
        Saying saying = sayingEntityService.getRandomSaying();
        boolean isMarked = bookmarkEntityService.isMarked(memberId, saying.getId());

        return DiarySayingDto.builder()
                .id(saying.getId())
                .contents(saying.getContents())
                .reference(saying.getReference())
                .isMarked(isMarked)
                .build();
    }
}
