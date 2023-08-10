package com.dnd.bbok.domain.checklist.controller;

import com.dnd.bbok.domain.checklist.dto.request.MemberChecklistRequestDto;
import com.dnd.bbok.domain.checklist.dto.response.BasicChecklistDto;
import com.dnd.bbok.domain.checklist.service.BasicChecklistService;
import com.dnd.bbok.domain.checklist.service.MemberChecklistUseCaseService;
import com.dnd.bbok.global.jwt.SessionUser;
import com.dnd.bbok.global.response.DataResponse;
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

import java.lang.reflect.InvocationTargetException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/checklist")
@Api(tags = "체크리스트 관련 컨트롤러")
public class
ChecklistController {
    private final BasicChecklistService basicChecklistService;
    private final MemberChecklistUseCaseService memberChecklistUseCaseService;

    @ApiOperation(value = "기본 체크리스트 제공")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public ResponseEntity<DataResponse<BasicChecklistDto>> getBasicChecklist() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        BasicChecklistDto basicChecklist = basicChecklistService.createWithEntity(BasicChecklistDto.class);
        return new ResponseEntity<>(
                DataResponse.of(HttpStatus.OK, "기본 체크리스트 제공 성공", basicChecklist), HttpStatus.OK);
    }

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
