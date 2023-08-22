package com.dnd.bbok.diary.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DiarySaying {
    @ApiModelProperty(value = "명언 Id")
    private final Long id;

    @ApiModelProperty(value = "명언 내용")
    private final String contents;

    @ApiModelProperty(value = "명언 출처")
    private final String reference;

    @ApiModelProperty(value = "북마크 되어있는지 여부")
    private final Boolean isMarked;

    @Builder
    public DiarySaying(Long id, String contents, String reference, Boolean isMarked) {
        this.id = id;
        this.contents = contents;
        this.reference = reference;
        this.isMarked = isMarked;
    }
}
