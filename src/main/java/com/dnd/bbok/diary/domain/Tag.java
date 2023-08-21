package com.dnd.bbok.diary.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class Tag {
    @ApiModelProperty(value = "태그 id")
    private Long id;

    @ApiModelProperty(value = "태그 이름")
    private final String tag;

    @ApiModelProperty(value = "다이어리 id")
    private final Long diaryId;

    public Tag(Long id, String tag) {
        this.id = id;
        this.tag = tag;
        this.diaryId = null;
    }

    public Tag(Long id, String tag, Long diaryId) {
        this.id = id;
        this.tag = tag;
        this.diaryId = diaryId;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
