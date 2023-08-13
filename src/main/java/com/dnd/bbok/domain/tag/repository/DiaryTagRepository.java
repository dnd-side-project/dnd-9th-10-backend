package com.dnd.bbok.domain.tag.repository;

import com.dnd.bbok.domain.tag.entity.DiaryTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiaryTagRepository extends JpaRepository<DiaryTag, Long> {
    @Query("SELECT dt FROM DiaryTag dt LEFT JOIN FETCH dt.friendTag WHERE dt.diary.id = :diaryId")
    Optional<DiaryTag> findByDiaryId(Long diaryId);

    @Query("SELECT dt FROM DiaryTag dt LEFT JOIN FETCH dt.friendTag LEFT JOIN FETCH dt.diary WHERE dt.diary.id IN :diaryIds")
    List<DiaryTag> findByDiaryIds(List<Long> diaryIds);
}
