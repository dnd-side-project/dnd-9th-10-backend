package com.dnd.bbok.diary.adapter.out.persistence.mapper;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryChecklistEntity;
import com.dnd.bbok.diary.domain.DiaryChecklist;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiaryChecklistMapper {
    public List<DiaryChecklist> toDomain(List<DiaryChecklistEntity> diaryChecklistEntities) {
        return diaryChecklistEntities.stream().map(ele -> new DiaryChecklist(ele.getId(), ele.isChecked(), ele.getMemberChecklistEntity().getCriteria(), ele.getDiaryEntity().getId(), ele.getMemberChecklistEntity().isGood())).collect(Collectors.toList());
    }
}
