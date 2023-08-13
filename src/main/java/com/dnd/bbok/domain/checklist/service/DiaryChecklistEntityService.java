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
import java.util.Optional;

/**
 * Diary Checklist Entity 관련한 DB 작업을 수행하는 서비스
 */
@Service
@RequiredArgsConstructor
public class DiaryChecklistEntityService {
    private final DiaryChecklistRepository diaryChecklistRepository;

    public void createDiaryChecklist(Diary diary, List<MemberChecklist> memberChecklist, List<ChecklistDto> checklistDtos) {

        memberChecklist.forEach(checklist -> {
            Optional<ChecklistDto> target = checklistDtos.stream().filter(ele -> Objects.equals(ele.getId(), checklist.getId())).findFirst();
            target.ifPresent(checklistDto -> this.diaryChecklistRepository.save(DiaryChecklist.builder()
                    .diary(diary)
                    .isChecked(checklistDto.getIsChecked())
                    .memberChecklist(checklist)
                    .build()));
        });
    }

    public List<DiaryChecklist> getDiaryChecklistByDiaryIds(List<Long> diaryIds) {
        return this.diaryChecklistRepository.getDiaryChecklistByDiaryIds(diaryIds);
    }
}
