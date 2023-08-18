package com.dnd.bbok.domain.saying.service;

import com.dnd.bbok.saying.adapter.out.persistence.entity.SayingEntity;
import com.dnd.bbok.saying.adapter.out.persistence.repository.SayingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Saying Entity 와 관련된 DB 작업을 수행하는 서비스
 */
@Service
@RequiredArgsConstructor
public class SayingEntityService {
    private final SayingRepository sayingRepository;
    public List<SayingEntity> getAllSaying() {
        return this.sayingRepository.findAll();
    }

}
