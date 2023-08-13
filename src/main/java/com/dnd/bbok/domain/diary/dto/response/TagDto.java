package com.dnd.bbok.domain.diary.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class TagDto {
    @ApiModelProperty(value = "태그 id")
    private final Long id;

    @ApiModelProperty(value = "태그 이름")
    private final String tag;


    public TagDto(long id, String tag) {
        this.id = id;
        this.tag = tag;
    }
}
