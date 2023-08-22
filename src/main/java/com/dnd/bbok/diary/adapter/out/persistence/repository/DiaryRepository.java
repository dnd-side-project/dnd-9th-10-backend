package com.dnd.bbok.diary.adapter.out.persistence.repository;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    @Query("select count(*) from DiaryEntity d where d.friend.id = :friendId")
    int countDiariesByFriendId(@Param("friendId") Long friendId);

    @Query(value = "SELECT DISTINCT d FROM DiaryEntity d "
            + "LEFT JOIN d.diaryTags dt "
            + "LEFT JOIN dt.friendTagEntity ft "
            + "WHERE d.friend.id = :friendId "
            + "AND d.contents LIKE %:keyword%"
            + "AND ft.name LIKE %:tag%",
    countQuery = "SELECT COUNT(DISTINCT d) FROM DiaryEntity d "
            + "LEFT JOIN d.diaryTags dt "
            + "LEFT JOIN dt.friendTagEntity ft "
            + "WHERE d.friend.id = :friendId "
            + "AND d.contents LIKE %:keyword%"
            + "AND ft.name LIKE %:tag%")
    Page<DiaryEntity> findDiaries(Long friendId, String keyword, String tag, Pageable pageable);

    List<DiaryEntity> findAllByFriendId(Long friendId);
}
