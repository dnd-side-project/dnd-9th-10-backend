package com.dnd.bbok.domain.diary.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class DiaryCreateDto {
    @ApiModelProperty(value = "명언 정보")
    private final DiarySayingDto saying;

    @ApiModelProperty(value = "친구 적합도")
    private final Long friendPercentage;

    public DiaryCreateDto(DiarySayingDto saying, Long friendPercentage) {
        this.saying = saying;
        this.friendPercentage = friendPercentage;
    }
}
