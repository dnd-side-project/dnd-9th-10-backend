package com.dnd.bbok.domain.diary.dto.response;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class DiaryStickerDto {
    private final ArrayList<StickerDto> stickers;

    public DiaryStickerDto() {
        this.stickers = new ArrayList<>();
        this.stickers.add(new StickerDto());
    }
}
