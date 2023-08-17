package com.dnd.bbok.member.adapter.in.web;

import com.dnd.bbok.member.application.port.in.CreateMemberChecklistRequest;
import com.dnd.bbok.domain.jwt.dto.SessionUser;
import com.dnd.bbok.global.response.MessageResponse;
import com.dnd.bbok.member.application.port.in.CreateMemberChecklistUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/v1/checklist")
@Api(tags = "체크리스트 관련 컨트롤러")
public class CreateMemberChecklistController {
    private final CreateMemberChecklistUseCase createMemberChecklistUseCase;

    @ApiOperation(value = "사용자 정의 체크리스트 추가")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public ResponseEntity<MessageResponse> createChecklist(
            @RequestBody CreateMemberChecklistRequest memberChecklist,
            @AuthenticationPrincipal SessionUser sessionUser
    ) {
        log.info(String.valueOf(sessionUser.getUuid()));
        createMemberChecklistUseCase.createMemberChecklist(sessionUser.getUuid(), memberChecklist);
        return new ResponseEntity<>(
                MessageResponse.of(HttpStatus.CREATED, "체크리스트 추가 성공"), HttpStatus.CREATED);
    }
}
