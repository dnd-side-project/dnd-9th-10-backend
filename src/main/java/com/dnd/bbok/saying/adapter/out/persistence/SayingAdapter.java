package com.dnd.bbok.saying.adapter.out.persistence;

import static com.dnd.bbok.global.exception.ErrorCode.SAYING_NOT_FOUND;

import com.dnd.bbok.global.exception.BusinessException;
import com.dnd.bbok.saying.adapter.out.persistence.entity.SayingEntity;
import com.dnd.bbok.saying.adapter.out.persistence.mapper.SayingMapper;
import com.dnd.bbok.saying.adapter.out.persistence.repository.SayingRepository;
import com.dnd.bbok.saying.application.port.out.LoadSayingPort;
import com.dnd.bbok.saying.domain.Saying;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SayingAdapter implements LoadSayingPort {
    private final SayingRepository sayingRepository;
    private final SayingMapper sayingMapper;

    @Override
    public List<Saying> getAllSaying() {
        List<SayingEntity> sayingEntities = sayingRepository.findAll();
        return sayingEntities.stream().map(sayingMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Saying getSaying(Long sayingId) {
        SayingEntity saying = sayingRepository.findSayingById(sayingId)
            .orElseThrow(() -> new BusinessException(SAYING_NOT_FOUND));
        return sayingMapper.toDomain(saying);
    }
}
