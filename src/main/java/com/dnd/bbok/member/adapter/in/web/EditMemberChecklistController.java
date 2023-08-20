package com.dnd.bbok.member.adapter.in.web;

import com.dnd.bbok.member.application.port.in.request.EditMemberChecklistRequest;
import com.dnd.bbok.member.application.port.in.response.SessionUser;
import com.dnd.bbok.member.application.port.in.usecase.EditMemberChecklistUseCase;
import com.dnd.bbok.global.response.MessageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "친구 관련 컨트롤러")
@RequiredArgsConstructor
public class EditMemberChecklistController {

  private final EditMemberChecklistUseCase editMemberChecklistUseCase;

  @ApiOperation(value = "나만의 기준 수정")
  @PatchMapping("/member/checklist")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<MessageResponse> editChecklist(
      @RequestBody EditMemberChecklistRequest memberChecklistRequest,
      @AuthenticationPrincipal SessionUser sessionUser
  ) {
    editMemberChecklistUseCase.edit(sessionUser.getUuid(), memberChecklistRequest);
    return new ResponseEntity<>(MessageResponse.of(HttpStatus.OK, "기준 수정 성공"), HttpStatus.OK);
  }

}
