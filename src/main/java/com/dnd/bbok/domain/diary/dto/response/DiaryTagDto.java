package com.dnd.bbok.domain.diary.dto.response;

import com.dnd.bbok.domain.tag.entity.FriendTag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DiaryTagDto {
    @ApiModelProperty(value = "태그 목록")
    private final ArrayList<TagDto> tags;

    public DiaryTagDto(List<FriendTag> tags) {
        this.tags = new ArrayList<>();
        tags.forEach(tag -> {
            this.tags.add(new TagDto(tag.getId(), tag.getName()));
        });
    }
}
