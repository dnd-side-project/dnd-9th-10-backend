package com.dnd.bbok.diary.adapter.in.web;


import com.dnd.bbok.diary.application.port.in.request.CreateDiaryRequest;
import com.dnd.bbok.diary.application.port.in.response.CreateDiaryResponse;
import com.dnd.bbok.diary.application.port.in.usecase.CreateDiaryUseCase;
import com.dnd.bbok.global.response.DataResponse;
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
public class CreateDiaryController {
    private final CreateDiaryUseCase createDiaryUseCase;

    @ApiOperation(value = "일기 등록")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("{id}/diary")
    public ResponseEntity<DataResponse<CreateDiaryResponse>> createDiary(
            @Parameter(name = "id", in = ParameterIn.PATH, description = "친구 id") @PathVariable("id") Long id,
            @RequestBody CreateDiaryRequest createDiaryRequest,
            @AuthenticationPrincipal SessionUser sessionUser
    ){
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK , "일기 생성 성공", createDiaryUseCase.createDiary(sessionUser.getUuid(), id, createDiaryRequest)), HttpStatus.OK);
    }
}
