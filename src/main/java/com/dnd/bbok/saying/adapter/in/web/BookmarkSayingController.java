package com.dnd.bbok.saying.adapter.in.web;

import com.dnd.bbok.global.response.MessageResponse;
import com.dnd.bbok.member.application.port.in.response.SessionUser;
import com.dnd.bbok.saying.application.port.in.request.BookmarkRequestDto;
import com.dnd.bbok.saying.application.port.in.usecase.SaveBookmarkUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookmark")
@RequiredArgsConstructor
@Api(tags = "북마크 관련 컨트롤러")
public class BookmarkSayingController {

  private final SaveBookmarkUseCase saveBookmarkUseCase;

  @ApiOperation(value = "북마크 추가")
  @PostMapping("")
  public ResponseEntity<MessageResponse> createBookmark(
      @RequestBody BookmarkRequestDto bookmarkRequest,
      @AuthenticationPrincipal SessionUser sessionUser
  ) {
    saveBookmarkUseCase.bookmarkSaying(sessionUser.getUuid(), bookmarkRequest);
    return new ResponseEntity<>(
        MessageResponse.of(HttpStatus.CREATED, "북마크 등록 성공"), HttpStatus.CREATED);
  }

}
