package com.dnd.bbok.diary.adapter.out.persistence.repository;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiaryTagRepository extends JpaRepository<DiaryTagEntity, Long> {
    @Query("SELECT dt FROM DiaryTagEntity dt LEFT JOIN FETCH dt.friendTagEntity WHERE dt.diaryEntity.id = :diaryId")
    List<DiaryTagEntity> findByDiaryId(Long diaryId);

    @Query("SELECT dt FROM DiaryTagEntity dt LEFT JOIN FETCH dt.friendTagEntity LEFT JOIN FETCH dt.diaryEntity WHERE dt.diaryEntity.id IN :diaryIds")
    List<DiaryTagEntity> findByDiaryIds(List<Long> diaryIds);
}
