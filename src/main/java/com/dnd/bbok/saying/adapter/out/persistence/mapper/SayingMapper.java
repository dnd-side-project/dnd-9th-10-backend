package com.dnd.bbok.saying.adapter.out.persistence.mapper;

import com.dnd.bbok.saying.adapter.out.persistence.entity.SayingEntity;
import com.dnd.bbok.saying.domain.Saying;
import org.springframework.stereotype.Component;

@Component
public class SayingMapper {
    public Saying toDomain(SayingEntity sayingEntity) {
        return new Saying(sayingEntity.getId(), sayingEntity.getContents(), sayingEntity.getReference());
    }

    public SayingEntity toEntity(Saying saying) {
        return new SayingEntity(saying.getId(), saying.getContents(), saying.getReference());
    }
}
