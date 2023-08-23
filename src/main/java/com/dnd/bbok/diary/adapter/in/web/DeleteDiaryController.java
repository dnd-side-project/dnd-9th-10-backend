package com.dnd.bbok.diary.adapter.in.web;


import com.dnd.bbok.diary.application.port.in.usecase.DeleteDiaryUseCase;
import com.dnd.bbok.global.response.MessageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/friend")
@Api(tags = "일기 관련 컨트롤러")
public class DeleteDiaryController {
    private final DeleteDiaryUseCase deleteDiaryUseCase;

    @ApiOperation("일기 삭제")
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("diary/{id}")
    public ResponseEntity<MessageResponse> deleteDiary(@Parameter(name = "id", in = ParameterIn.PATH, description = "일기 id") @PathVariable("id") Long id) {
        deleteDiaryUseCase.deleteDiary(id);
        return new ResponseEntity<>(MessageResponse.of(HttpStatus.OK, "일기 삭제 성공"), HttpStatus.OK);
    }
}
