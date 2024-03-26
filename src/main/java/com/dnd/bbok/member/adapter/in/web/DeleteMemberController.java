package com.dnd.bbok.member.adapter.in.web;

import com.dnd.bbok.global.response.MessageResponse;
import com.dnd.bbok.member.application.port.in.response.SessionUser;
import com.dnd.bbok.member.application.port.in.usecase.DeleteMemberUseCase;
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
@RequestMapping("/api/v1")
@Api(tags = "멤버 관련 컨트롤러")
@RequiredArgsConstructor
public class DeleteMemberController {
    private final DeleteMemberUseCase deleteMemberUseCase;

    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping("/member")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<MessageResponse> getChecklist(
            @AuthenticationPrincipal SessionUser sessionUser
    ) {
        log.info(String.valueOf(sessionUser.getUuid()));
        String result = deleteMemberUseCase.deleteMember(sessionUser.getUuid());
        return new ResponseEntity<>(
                MessageResponse.of(HttpStatus.CREATED, result), HttpStatus.CREATED);
    }
}
