package com.dnd.bbok.saying.adapter.in.web;

import com.dnd.bbok.global.response.MessageResponse;
import com.dnd.bbok.member.application.port.in.response.SessionUser;
import com.dnd.bbok.saying.application.port.in.usecase.DeleteBookmarkUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookmark")
@RequiredArgsConstructor
@Api(tags = "북마크 관련 컨트롤러")
public class DeleteBookmarkController {

  private final DeleteBookmarkUseCase deleteBookmarkUseCase;

  @ApiOperation(value = "북마크 삭제")
  @PreAuthorize("isAuthenticated()")
  @DeleteMapping("/{id}")
  public ResponseEntity<MessageResponse> deleteBookmark(
      @PathVariable("id") Long sayingId,
      @AuthenticationPrincipal SessionUser sessionUser) {
    deleteBookmarkUseCase.deleteBookmark(sessionUser.getUuid(), sayingId);
    return new ResponseEntity<>(MessageResponse.of(HttpStatus.OK, "북마크 취소 성공"), HttpStatus.OK);
  }

}
