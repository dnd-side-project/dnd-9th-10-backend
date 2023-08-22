package com.dnd.bbok.diary.application.port.in.response;

import com.dnd.bbok.diary.domain.Sticker;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class GetDiaryStickerResponse {
    private final ArrayList<StickerDto> stickers;

    public GetDiaryStickerResponse() {
        this.stickers = new ArrayList<>();
        this.stickers.add(new StickerDto());
    }

    @Getter
    public static class StickerDto {
        @ApiModelProperty(value = "스티커 이름")
        private final Sticker name;

        @ApiModelProperty(value = "스티커 다운로드 주소")
        private final String stickerUrl;

        public StickerDto() {
            this.name = Sticker.POOP;
            this.stickerUrl = "https://i.ibb.co/6v7YXHZ/cactus.png";
        }
    }
}
