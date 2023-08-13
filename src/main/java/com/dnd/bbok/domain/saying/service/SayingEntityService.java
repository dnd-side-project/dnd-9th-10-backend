package com.dnd.bbok.domain.saying.service;

import com.dnd.bbok.domain.saying.entity.Saying;
import com.dnd.bbok.domain.saying.repository.SayingRepository;
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
    public List<Saying> getAllSaying() {
        return this.sayingRepository.findAll();
    }

}
