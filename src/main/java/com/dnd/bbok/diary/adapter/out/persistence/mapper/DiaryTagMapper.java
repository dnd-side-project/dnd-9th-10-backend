package com.dnd.bbok.diary.adapter.out.persistence.mapper;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryEntity;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryTagEntity;
import com.dnd.bbok.diary.domain.Tag;
import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendEntity;
import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendTagEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DiaryTagMapper {
    public List<Tag> toDomain(List<DiaryTagEntity> diaryTagEntities) {
        return diaryTagEntities.stream().map(ele -> new Tag(ele.getId(), ele.getFriendTagEntity().getId(), ele.getFriendTagEntity().getName(), ele.getDiaryEntity().getId())).collect(Collectors.toList());
    }

    public List<DiaryTagEntity> toEntity(List<Tag> tags, DiaryEntity diaryEntity, List<DiaryTagEntity> prevDiaryTags, FriendEntity friendEntity, List<FriendTagEntity> friendTagEntities) {
        List<DiaryTagEntity> diaryTagEntities = new ArrayList<>();
        tags.forEach(ele -> {
            Optional<DiaryTagEntity> diaryTagEntity =  prevDiaryTags.stream().filter(prevTag -> Objects.equals(prevTag.getFriendTagEntity().getName(), ele.getTag())).findFirst();
            if (diaryTagEntity.isPresent()) {
                diaryTagEntities.add(diaryTagEntity.get());
            } else {
                FriendTagEntity friendTagEntity = friendTagEntities.stream().filter(entity -> Objects.equals(entity.getFriend(), friendEntity) && Objects.equals(entity.getName(), ele.getTag())).findFirst().orElseThrow();
                diaryTagEntities.add(DiaryTagEntity.builder()
                        .diaryEntity(diaryEntity)
                        .friendTagEntity(friendTagEntity)
                        .build());
            }
        });
        return diaryTagEntities;
    }
}
