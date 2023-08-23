package com.dnd.bbok.diary.adapter.in.web;

import com.dnd.bbok.diary.application.port.in.response.GetDiaryStickerResponse;
import com.dnd.bbok.diary.application.port.in.usecase.GetStickerQuery;
import com.dnd.bbok.global.response.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friend")
@Api(tags = "일기 관련 컨트롤러")
public class GetStickerController {
    private final GetStickerQuery getStickerQuery;
    @ApiOperation("스티커 목록 조회")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/diary/sticker")
    public ResponseEntity<DataResponse<GetDiaryStickerResponse>> getStickers() {
        GetDiaryStickerResponse getDiaryStickerResponse = getStickerQuery.getStickerQuery();
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "스티커 목록 조회 성공", getDiaryStickerResponse), HttpStatus.OK);
    }
}
