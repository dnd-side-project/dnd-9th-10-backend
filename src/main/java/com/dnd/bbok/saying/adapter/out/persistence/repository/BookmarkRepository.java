package com.dnd.bbok.saying.adapter.out.persistence.repository;

import com.dnd.bbok.saying.adapter.out.persistence.entity.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {
    Optional<BookmarkEntity> findByMemberIdAndSayingEntityId(UUID memberId, Long sayingId);
}
