package com.dnd.bbok.domain.saying.service;

import com.dnd.bbok.domain.diary.dto.response.DiarySayingDto;
import com.dnd.bbok.domain.saying.entity.Saying;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
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
        List<Saying> sayings = sayingEntityService.getAllSaying();
        Random random = new Random();
        int index = random.nextInt(sayings.size());
        Saying saying = sayings.get(index);
        
        boolean isMarked = bookmarkEntityService.isMarked(memberId, saying.getId());

        return DiarySayingDto.builder()
                .id(saying.getId())
                .contents(saying.getContents())
                .reference(saying.getReference())
                .isMarked(isMarked)
                .build();
    }
}
