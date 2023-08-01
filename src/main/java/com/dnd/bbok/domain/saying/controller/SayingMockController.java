package com.dnd.bbok.domain.saying.controller;

import com.dnd.bbok.domain.saying.dto.response.BookmarkInfoDto;
import com.dnd.bbok.global.response.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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


}
