package com.dnd.bbok.domain.checklist.service;

import com.dnd.bbok.domain.checklist.entity.DiaryChecklist;
import com.dnd.bbok.domain.checklist.entity.MemberChecklist;
import com.dnd.bbok.domain.diary.dto.request.ChecklistDto;
import com.dnd.bbok.domain.diary.entity.Diary;
import com.dnd.bbok.domain.checklist.repository.DiaryChecklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Diary Checklist Entity 관련한 DB 작업을 수행하는 서비스
 */
@Service
@RequiredArgsConstructor
public class DiaryChecklistEntityService {
    private final DiaryChecklistRepository diaryChecklistRepository;

    public void createDiaryChecklist(Diary diary, List<MemberChecklist> memberChecklist, List<ChecklistDto> checklistDtos) {
        memberChecklist.forEach(checklist -> {
            this.diaryChecklistRepository.save(DiaryChecklist.builder()
                    .diary(diary)
                    .isChecked((checklistDtos.stream().filter(ele -> Objects.equals(ele.getId(), checklist.getId())).findFirst().orElseThrow()).getIsChecked())
                    .memberChecklist(checklist)
                    .build()
            );
        });
    }
}
