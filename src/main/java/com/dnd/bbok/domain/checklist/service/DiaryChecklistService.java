package com.dnd.bbok.domain.checklist.service;

import com.dnd.bbok.domain.checklist.entity.DiaryChecklist;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberChecklistEntity;
import com.dnd.bbok.domain.diary.dto.request.ChecklistDto;
import com.dnd.bbok.domain.diary.entity.Diary;
import com.dnd.bbok.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.dnd.bbok.global.exception.ErrorCode.INVALID_MEMBER_CHECKLIST_ID;

@Service
@RequiredArgsConstructor
public class DiaryChecklistService {
    private static final int NEED_CHECKLIST_COUNT = 10;
    private final MemberChecklistEntityService memberChecklistEntityService;
    private final DiaryChecklistEntityService diaryChecklistEntityService;

    public void createDiaryChecklist(Diary diary, List<ChecklistDto> checklist) {
        List<MemberChecklistEntity> memberChecklistEntity = memberChecklistEntityService.getMemberChecklistInIds(checklist.stream().map(ChecklistDto::getId).collect(Collectors.toList()));

        if (memberChecklistEntity.size() != NEED_CHECKLIST_COUNT) {
           throw new BusinessException(INVALID_MEMBER_CHECKLIST_ID);
        }

        diaryChecklistEntityService.createDiaryChecklist(diary, memberChecklistEntity, checklist);
    }

    public List<DiaryChecklist> getDiaryChecklistByDiaryIds(List<Long> diaryIds) {
        return this.diaryChecklistEntityService.getDiaryChecklistByDiaryIds(diaryIds);
    }
}
