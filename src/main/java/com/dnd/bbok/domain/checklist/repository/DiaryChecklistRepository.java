package com.dnd.bbok.domain.checklist.repository;

import com.dnd.bbok.domain.checklist.entity.DiaryChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiaryChecklistRepository extends JpaRepository<DiaryChecklist, Long> {
    @Query("SELECT dc FROM DiaryChecklist dc JOIN FETCH dc.diary JOIN FETCH dc.memberChecklistEntity WHERE dc.diary.id IN :diaryIds")
    List<DiaryChecklist> getDiaryChecklistByDiaryIds(List<Long> diaryIds);
}
