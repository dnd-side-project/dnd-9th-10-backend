package com.dnd.bbok.diary.adapter.out.persistence.mapper;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryTagEntity;
import com.dnd.bbok.diary.domain.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiaryTagMapper {
    public List<Tag> toDomain(List<DiaryTagEntity> diaryTagEntities) {
        return diaryTagEntities.stream().map(ele -> new Tag(ele.getId(), ele.getFriendTagEntity().getName(), ele.getDiaryEntity().getId())).collect(Collectors.toList());
    }
}
