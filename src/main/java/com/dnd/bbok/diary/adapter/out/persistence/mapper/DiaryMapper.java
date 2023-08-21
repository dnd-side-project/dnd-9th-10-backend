package com.dnd.bbok.diary.adapter.out.persistence.mapper;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryEntity;
import com.dnd.bbok.diary.domain.Diary;
import com.dnd.bbok.diary.domain.DiaryChecklist;
import com.dnd.bbok.diary.domain.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DiaryMapper {
    public Diary toEntity(DiaryEntity diaryEntity, List<Tag> tags, List<DiaryChecklist> diaryChecklists) {
        return new Diary(diaryEntity.getId(), diaryEntity.getEmoji(), diaryEntity.getContents(), diaryEntity.getDiaryDate(), diaryEntity.getSticker(), tags, diaryChecklists);
    }

    public List<Diary> toEntities(List<DiaryEntity> diaryEntities, List<Tag> tags, List<DiaryChecklist> diaryChecklists) {
        return diaryEntities.stream().map(diaryEntity -> {
            List<Tag> targetTags = tags.stream().filter(tag -> Objects.equals(tag.getDiaryId(), diaryEntity.getId())).collect(Collectors.toList());
            List<DiaryChecklist> targetChecklists = diaryChecklists.stream().filter(diaryChecklist -> Objects.equals(diaryChecklist.getDiaryId(), diaryEntity.getId())).collect(Collectors.toList());
            return new Diary(diaryEntity.getId(), diaryEntity.getEmoji(), diaryEntity.getContents(), diaryEntity.getDiaryDate(), diaryEntity.getSticker(), targetTags, targetChecklists);
        }).collect(Collectors.toList());
    }
}
