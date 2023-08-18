package com.dnd.bbok.diary.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class Tag {
    @ApiModelProperty(value = "태그 id")
    private Long id;

    @ApiModelProperty(value = "태그 이름")
    private final String tag;


    public Tag(Long id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
