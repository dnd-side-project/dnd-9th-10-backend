package com.dnd.bbok.domain.saying.repository;

import com.dnd.bbok.domain.saying.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findByMemberIdAndSayingId(UUID memberId, Long sayingId);
}
