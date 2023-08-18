package com.dnd.bbok.domain.saying.service;

import com.dnd.bbok.diary.application.port.in.response.DiarySaying;
import com.dnd.bbok.saying.adapter.out.persistence.entity.SayingEntity;
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

    public DiarySaying getRandomSaying(UUID memberId) {
        List<SayingEntity> sayingEntities = sayingEntityService.getAllSaying();
        Random random = new Random();
        int index = random.nextInt(sayingEntities.size());
        SayingEntity sayingEntity = sayingEntities.get(index);
        
        boolean isMarked = bookmarkEntityService.isMarked(memberId, sayingEntity.getId());

        return DiarySaying.builder()
                .id(sayingEntity.getId())
                .contents(sayingEntity.getContents())
                .reference(sayingEntity.getReference())
                .isMarked(isMarked)
                .build();
    }
}
