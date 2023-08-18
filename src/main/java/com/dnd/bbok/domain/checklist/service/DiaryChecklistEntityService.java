package com.dnd.bbok.domain.checklist.service;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryChecklistEntity;
import com.dnd.bbok.diary.application.port.in.request.CreateDiaryRequest;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberChecklistEntity;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryEntity;
import com.dnd.bbok.diary.adapter.out.persistence.repository.DiaryChecklistRepository;
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

    public void createDiaryChecklist(DiaryEntity diaryEntity, List<MemberChecklistEntity> memberChecklistEntity, List<CreateDiaryRequest.Checklist> checklistDtos) {

        memberChecklistEntity.forEach(checklist -> {
            Optional<CreateDiaryRequest.Checklist> target = checklistDtos.stream().filter(ele -> Objects.equals(ele.getId(), checklist.getId())).findFirst();
            target.ifPresent(checklistDto -> this.diaryChecklistRepository.save(DiaryChecklistEntity.builder()
                    .diaryEntity(diaryEntity)
                    .isChecked(checklistDto.getIsChecked())
                    .memberChecklistEntity(checklist)
                    .build()));
        });
    }

    public List<DiaryChecklistEntity> getDiaryChecklistByDiaryIds(List<Long> diaryIds) {
        return this.diaryChecklistRepository.getDiaryChecklistByDiaryIds(diaryIds);
    }
}
