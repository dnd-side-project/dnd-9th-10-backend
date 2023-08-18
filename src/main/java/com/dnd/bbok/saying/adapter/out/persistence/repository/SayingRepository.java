package com.dnd.bbok.saying.adapter.out.persistence.repository;

import com.dnd.bbok.saying.adapter.out.persistence.entity.SayingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SayingRepository extends JpaRepository<SayingEntity, Long> {
}
