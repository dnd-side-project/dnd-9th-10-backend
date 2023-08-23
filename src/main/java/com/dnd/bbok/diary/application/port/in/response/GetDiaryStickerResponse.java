package com.dnd.bbok.diary.application.port.in.response;

import com.dnd.bbok.diary.domain.Sticker;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class GetDiaryStickerResponse {
    private final ArrayList<StickerDto> stickers;

    public GetDiaryStickerResponse(ArrayList<StickerDto> stickers) {
        this.stickers = stickers;
    }

    @Getter
    public static class StickerDto {
        @ApiModelProperty(value = "스티커 이름")
        private final Sticker name;

        @ApiModelProperty(value = "스티커 다운로드 주소")
        private final String stickerUrl;

        public StickerDto(Sticker name, String stickerUrl) {
            this.name = name;
            this.stickerUrl = stickerUrl;
        }
    }
}
