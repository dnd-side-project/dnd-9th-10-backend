package com.dnd.bbok.diary.adapter.out.persistence.mapper;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryChecklistEntity;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryEntity;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryTagEntity;
import com.dnd.bbok.diary.domain.Diary;
import com.dnd.bbok.diary.domain.DiaryChecklist;
import com.dnd.bbok.diary.domain.Tag;
import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DiaryMapper {
    public Diary toDomain(DiaryEntity diaryEntity, List<Tag> tags, List<DiaryChecklist> diaryChecklists) {
        List<Tag> targetTags = tags.stream().filter(tag -> Objects.equals(tag.getDiaryId(), diaryEntity.getId())).collect(Collectors.toList());
        List<DiaryChecklist> targetChecklists = diaryChecklists.stream().filter(diaryChecklist -> Objects.equals(diaryChecklist.getDiaryId(), diaryEntity.getId())).collect(Collectors.toList());
        return new Diary(diaryEntity.getId(), diaryEntity.getFriend().getId(), diaryEntity.getEmoji(), diaryEntity.getContents(), diaryEntity.getDiaryDate(), diaryEntity.getSticker(), diaryEntity.getDiaryScore(), diaryEntity.getIsDeleted(), targetTags, targetChecklists);
    }

    public DiaryEntity toEntity(Diary diary, FriendEntity friendEntity) {
        return DiaryEntity.builder()
                .id(diary.getId())
                .friend(friendEntity)
                .contents(diary.getContents())
                .diaryDate(diary.getDiaryDate())
                .diaryScore(diary.getDiaryScore())
                .emoji(diary.getEmoji())
                .sticker(diary.getSticker())
                .isDeleted(diary.getIsDeleted())
                .build();
    }
}
