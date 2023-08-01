package com.dnd.bbok.domain.saying.controller;

import com.dnd.bbok.domain.saying.dto.request.BookmarkRequestDto;
import com.dnd.bbok.domain.saying.dto.response.BookmarkInfoDto;
import com.dnd.bbok.global.response.DataResponse;
import com.dnd.bbok.global.response.MessageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookmark")
@Api(tags = "Mock 북마크 관련 컨트롤러")
public class SayingMockController {

  @ApiOperation(value = "북마크 목록 조회")
  @GetMapping("")
  public ResponseEntity<DataResponse<BookmarkInfoDto>> getBookmarks() {
    BookmarkInfoDto bookmarks = new BookmarkInfoDto();
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.OK, "북마크 목록 조회 성공", bookmarks), HttpStatus.OK);
  }

  @ApiOperation(value = "북마크 추가")
  @PostMapping("")
  public ResponseEntity<MessageResponse> createBookmark(
      @RequestBody BookmarkRequestDto bookmarkRequest
  ) {
    return new ResponseEntity<>(
        MessageResponse.of(HttpStatus.CREATED, "북마크 등록 성공"), HttpStatus.CREATED);
  }

}
