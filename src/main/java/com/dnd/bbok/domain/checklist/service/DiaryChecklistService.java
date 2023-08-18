package com.dnd.bbok.domain.checklist.service;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryChecklistEntity;
import com.dnd.bbok.diary.application.port.in.request.CreateDiaryRequest;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberChecklistEntity;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryEntity;
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

    public void createDiaryChecklist(DiaryEntity diaryEntity, List<CreateDiaryRequest.Checklist> checklist) {
        List<MemberChecklistEntity> memberChecklistEntity = memberChecklistEntityService.getMemberChecklistInIds(checklist.stream().map(CreateDiaryRequest.Checklist::getId).collect(Collectors.toList()));

        if (memberChecklistEntity.size() != NEED_CHECKLIST_COUNT) {
           throw new BusinessException(INVALID_MEMBER_CHECKLIST_ID);
        }

        diaryChecklistEntityService.createDiaryChecklist(diaryEntity, memberChecklistEntity, checklist);
    }

    public List<DiaryChecklistEntity> getDiaryChecklistByDiaryIds(List<Long> diaryIds) {
        return this.diaryChecklistEntityService.getDiaryChecklistByDiaryIds(diaryIds);
    }
}
