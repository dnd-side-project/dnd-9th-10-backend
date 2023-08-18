package com.dnd.bbok.saying.adapter.out.persistence;

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
        return sayingEntities.stream().map(sayingMapper::toEntity).collect(Collectors.toList());
    }
}
