package com.dnd.bbok.saying.adapter.in.web;

import com.dnd.bbok.global.response.DataResponse;
import com.dnd.bbok.member.application.port.in.response.SessionUser;
import com.dnd.bbok.saying.application.port.in.response.GetBookmarkGroupResponse;
import com.dnd.bbok.saying.application.port.in.usecase.GetBookmarksQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookmark")
@RequiredArgsConstructor
@Api(tags = "북마크 관련 컨트롤러")
public class GetBookmarkController {

  private final GetBookmarksQuery getBookmarksQuery;

  @ApiOperation(value = "북마크 목록 조회")
  @PreAuthorize("isAuthenticated()")
  @GetMapping("")
  public ResponseEntity<DataResponse<GetBookmarkGroupResponse>> getBookmarks(
      @AuthenticationPrincipal SessionUser sessionUser
  ) {
    GetBookmarkGroupResponse bookmarks = getBookmarksQuery.getBookmarks(sessionUser.getUuid());
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.OK, "북마크 목록 조회 성공", bookmarks), HttpStatus.OK);
  }

}
