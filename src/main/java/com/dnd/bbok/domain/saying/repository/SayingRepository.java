package com.dnd.bbok.domain.saying.repository;

import com.dnd.bbok.domain.saying.entity.Saying;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SayingRepository extends JpaRepository<Saying, Long> {
}
