package com.dnd.bbok.domain.diary.dto.response;

import com.dnd.bbok.diary.domain.Tag;
import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendTagEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DiaryTagDto {
    @ApiModelProperty(value = "태그 목록")
    private final ArrayList<Tag> tags;

    public DiaryTagDto(List<FriendTagEntity> tags) {
        this.tags = new ArrayList<>();
        tags.forEach(tag -> this.tags.add(new Tag(tag.getId(), tag.getName())));
    }
}
