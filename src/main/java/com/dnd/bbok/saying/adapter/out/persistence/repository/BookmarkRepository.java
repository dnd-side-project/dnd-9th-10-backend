package com.dnd.bbok.saying.adapter.out.persistence.repository;

import com.dnd.bbok.saying.adapter.out.persistence.entity.BookmarkEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {

    @Query("select b from BookmarkEntity b where b.saying.id = :sayingId and b.member.id = :memberId")
    Optional<BookmarkEntity> findBookmark(
        @Param("sayingId") Long sayingId, @Param("memberId") UUID memberId);

    @Query("select b from BookmarkEntity b where b.member.id = :memberId")
    List<BookmarkEntity> findMemberBookmark(@Param("memberId") UUID memberId);
}
