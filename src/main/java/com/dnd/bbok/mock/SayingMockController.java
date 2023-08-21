package com.dnd.bbok.mock;

import com.dnd.bbok.global.response.MessageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookmark")
@Api(tags = "Mock 북마크 관련 컨트롤러")
public class SayingMockController {

  @ApiOperation(value = "북마크 삭제")
  @DeleteMapping("/{id}")
  public ResponseEntity<MessageResponse> deleteBookmark(@PathVariable("id") Long sayingId) {
    return new ResponseEntity<>(
        MessageResponse.of(HttpStatus.OK, "북마크 취소 성공"), HttpStatus.OK
    );
  }

}
