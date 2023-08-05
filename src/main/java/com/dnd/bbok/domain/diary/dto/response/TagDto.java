package com.dnd.bbok.domain.diary.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class TagDto {
    @ApiModelProperty(value = "태그 id")
    private final int id;

    @ApiModelProperty(value = "태그 이름")
    private final String tag;

    public TagDto() {
        this.id = 1;
        this.tag = "가스라이팅";
    }
}
