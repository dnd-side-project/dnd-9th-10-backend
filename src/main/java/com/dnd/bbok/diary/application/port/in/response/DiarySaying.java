package com.dnd.bbok.diary.application.port.in.response;

import com.dnd.bbok.domain.saying.dto.response.SayingInfoDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DiarySaying extends SayingInfoDto {
    @ApiModelProperty(value = "북마크 되어있는지 여부")
    private final Boolean isMarked;

    @Builder
    public DiarySaying(Long id, String contents, String reference, Boolean isMarked) {
        super(id, contents, reference);
        this.isMarked = isMarked;
    }
}
