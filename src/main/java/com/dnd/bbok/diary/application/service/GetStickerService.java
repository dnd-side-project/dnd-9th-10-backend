package com.dnd.bbok.diary.application.service;

import com.dnd.bbok.diary.application.port.in.response.GetDiaryStickerResponse;
import com.dnd.bbok.diary.application.port.in.usecase.GetStickerQuery;
import com.dnd.bbok.diary.domain.Sticker;
import com.dnd.bbok.infra.s3.service.S3Downloader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class GetStickerService implements GetStickerQuery {
    private final S3Downloader s3Downloader;
    @Override
    public GetDiaryStickerResponse getStickerQuery() {
        ArrayList<GetDiaryStickerResponse.StickerDto> stickers = new ArrayList<>();

        for (Sticker sticker: Sticker.values()) {
            stickers.add(new GetDiaryStickerResponse.StickerDto(sticker, s3Downloader.getIconUrl(sticker.getIconFile())));
        }
        return new GetDiaryStickerResponse(stickers);
    }
}
