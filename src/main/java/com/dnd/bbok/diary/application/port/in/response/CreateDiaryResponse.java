package com.dnd.bbok.diary.application.port.in.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class CreateDiaryResponse {
    @ApiModelProperty(value = "명언 정보")
    private final DiarySaying saying;

    @ApiModelProperty(value = "친구 적합도")
    private final Long friendPercentage;

    public CreateDiaryResponse(DiarySaying saying, Long friendPercentage) {
        this.saying = saying;
        this.friendPercentage = friendPercentage;
    }
}
