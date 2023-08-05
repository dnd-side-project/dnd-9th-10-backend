package com.dnd.bbok.domain.diary.dto.response;

import com.dnd.bbok.domain.diary.entity.Sticker;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class StickerDto {
    @ApiModelProperty(value = "스티커 이름")
    private final Sticker name;

    @ApiModelProperty(value = "스티커 다운로드 주소")
    private final String stickerUrl;

    public StickerDto() {
        this.name = Sticker.POOP;
        this.stickerUrl = "https://i.ibb.co/6v7YXHZ/cactus.png";
    }
}
