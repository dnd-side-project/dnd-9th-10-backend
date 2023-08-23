package com.dnd.bbok.diary.adapter.in.web;

import com.dnd.bbok.diary.application.port.in.request.UpdateDiaryRequest;
import com.dnd.bbok.diary.application.port.in.response.CreateDiaryResponse;
import com.dnd.bbok.diary.application.port.in.usecase.UpdateDiaryUseCase;
import com.dnd.bbok.global.response.MessageResponse;
import com.dnd.bbok.member.application.port.in.response.SessionUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/friend")
@Api(tags = "일기 관련 컨트롤러")
public class UpdateDiaryController {
    private final UpdateDiaryUseCase updateDiaryUseCase;

    @ApiOperation("일기 수정")
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("diary/{id}")
    public ResponseEntity<MessageResponse> updateDiary(
            @Parameter(name = "id", in = ParameterIn.PATH, description = "일기 id") @PathVariable("id") Long id,
            @RequestBody UpdateDiaryRequest updateDiaryRequest,
            @AuthenticationPrincipal SessionUser sessionUser
    ) {
        updateDiaryUseCase.updateDiary(sessionUser.getUuid(), id, updateDiaryRequest);
        return new ResponseEntity<>(MessageResponse.of(HttpStatus.OK, "일기 수정 성공"), HttpStatus.OK);
    }
}
