package com.dnd.bbok.diary.adapter.out.persistence.mapper;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryChecklistEntity;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryEntity;
import com.dnd.bbok.diary.domain.DiaryChecklist;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberChecklistEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DiaryChecklistMapper {
    public List<DiaryChecklist> toDomain(List<DiaryChecklistEntity> diaryChecklistEntities) {
        return diaryChecklistEntities.stream().map(ele ->
                new DiaryChecklist(ele.getId(),
                        ele.getMemberChecklistEntity().getId(),
                        ele.isChecked(),
                        ele.getMemberChecklistEntity().getCriteria(),
                        ele.getDiaryEntity().getId(),
                        ele.getMemberChecklistEntity().isGood()))
                .collect(Collectors.toList());
    }

    public List<DiaryChecklistEntity> toEntity(List<DiaryChecklist> diaryChecklists,
                                               List<MemberChecklistEntity> memberChecklistEntities,
                                               DiaryEntity diaryEntity) {
        return diaryChecklists.stream().map(ele -> {
            MemberChecklistEntity memberChecklistEntity = memberChecklistEntities.stream().
                    filter(entity -> Objects.equals(entity.getId(), ele.getMemberChecklistId())).findFirst().orElseThrow();

            return DiaryChecklistEntity.builder()
                    .id(ele.getId())
                    .diaryEntity(diaryEntity)
                    .isChecked(ele.getIsChecked())
                    .memberChecklistEntity(memberChecklistEntity)
                    .build();
        }).collect(Collectors.toList());
    }
}
