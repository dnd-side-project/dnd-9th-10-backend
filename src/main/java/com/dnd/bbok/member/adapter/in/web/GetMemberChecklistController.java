package com.dnd.bbok.member.adapter.in.web;

import com.dnd.bbok.domain.jwt.dto.SessionUser;
import com.dnd.bbok.global.response.DataResponse;
import com.dnd.bbok.member.application.port.in.GetMemberChecklistQuery;
import com.dnd.bbok.member.application.port.in.GetMemberChecklistResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "친구 관련 컨트롤러")
@RequiredArgsConstructor
public class GetMemberChecklistController {
    private final GetMemberChecklistQuery getMemberChecklistQuery;

    @ApiOperation(value = "나만의 기준 조회")
    @GetMapping("/friend/checklist")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DataResponse<GetMemberChecklistResponse>> getChecklist(
        @AuthenticationPrincipal SessionUser sessionUser
    ) {
        //해당 uuid 를 가진 member 와 체크리스트를 들고온다.
        log.info(String.valueOf(sessionUser.getUuid()));
        GetMemberChecklistResponse myChecklist = getMemberChecklistQuery.getMemberChecklistQuery(sessionUser.getUuid());
        return new ResponseEntity<>(
                DataResponse.of(HttpStatus.OK, "기준 조회 성공", myChecklist), HttpStatus.OK);
    }
}
