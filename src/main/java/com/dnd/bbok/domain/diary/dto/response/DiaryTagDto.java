package com.dnd.bbok.domain.diary.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class DiaryTagDto {
    @ApiModelProperty(value = "태그 목록")
    private final ArrayList<TagDto> tags;

    public DiaryTagDto() {
        this.tags = new ArrayList<>();
        this.tags.add(new TagDto());
    }
}
