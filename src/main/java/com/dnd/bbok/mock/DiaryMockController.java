package com.dnd.bbok.mock;

import com.dnd.bbok.diary.application.port.in.response.GetDiaryStickerResponse;
import com.dnd.bbok.diary.application.port.in.response.CreateDiaryResponse;
import com.dnd.bbok.global.response.DataResponse;
import com.dnd.bbok.global.response.MessageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/friend")
@Api(tags = "Mock 일기 관련 컨트롤러")
public class DiaryMockController {
    @ApiOperation("스티커 목록 조회")
    @GetMapping("/diary/sticker")
    public ResponseEntity<DataResponse<GetDiaryStickerResponse>> getStickers() {
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "스티커 목록 조회 성공", new GetDiaryStickerResponse()), HttpStatus.OK);
    }

    @ApiOperation("일기 삭제")
    @DeleteMapping("diary/{id}")
    public ResponseEntity<MessageResponse> deleteDiary(@Parameter(name = "id", in = ParameterIn.PATH, description = "일기 id") @PathVariable("id") Long id) {
        return new ResponseEntity<>(MessageResponse.of(HttpStatus.OK, "일기 삭제 성공"), HttpStatus.OK);
    }
}
