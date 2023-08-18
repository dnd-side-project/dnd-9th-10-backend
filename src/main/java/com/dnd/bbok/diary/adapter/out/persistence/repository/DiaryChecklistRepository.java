package com.dnd.bbok.diary.adapter.out.persistence.repository;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryChecklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiaryChecklistRepository extends JpaRepository<DiaryChecklistEntity, Long> {
    @Query("SELECT dc FROM DiaryChecklistEntity dc JOIN FETCH dc.diaryEntity JOIN FETCH dc.memberChecklistEntity WHERE dc.diaryEntity.id IN :diaryIds")
    List<DiaryChecklistEntity> getDiaryChecklistByDiaryIds(List<Long> diaryIds);
}
