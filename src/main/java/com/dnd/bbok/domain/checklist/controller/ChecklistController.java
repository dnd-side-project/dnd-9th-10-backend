package com.dnd.bbok.domain.checklist.controller;

import com.dnd.bbok.domain.checklist.dto.request.MemberChecklistRequestDto;
import com.dnd.bbok.domain.checklist.service.MemberChecklistUseCaseService;
import com.dnd.bbok.domain.jwt.dto.SessionUser;
import com.dnd.bbok.global.response.MessageResponse;
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
@Api(tags = "체크리스트 관련 컨트롤러(Old)")
public class ChecklistController {
    private final MemberChecklistUseCaseService memberChecklistUseCaseService;

    @ApiOperation(value = "사용자 정의 체크리스트 추가")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public ResponseEntity<MessageResponse> createChecklist(
            @RequestBody MemberChecklistRequestDto memberChecklist,
            @AuthenticationPrincipal SessionUser sessionUser
    ) {
        log.info(String.valueOf(sessionUser.getUuid()));
        memberChecklistUseCaseService.createMemberChecklistEntities(memberChecklist, sessionUser.getUuid());
        return new ResponseEntity<>(
                MessageResponse.of(HttpStatus.CREATED, "체크리스트 추가 성공"), HttpStatus.CREATED);
    }
}
