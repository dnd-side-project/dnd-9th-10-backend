package com.dnd.bbok;

import com.dnd.bbok.global.response.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Health Controller: 서버 동작 체크")
@RestController
public class HealthController {

  @ApiOperation(value = "서버 동작 체크")
  @GetMapping("/health")
  public String healthCheck() {
    return "hello";
  }

  /**
   * Data reponse에 감싸져있는 HTTP status는 응답 body에 노출됨
   */
  @ApiOperation(value = "응답형식 예시")
  @GetMapping("/response")
  public ResponseEntity<DataResponse<String>> responseCheck() {
    return new ResponseEntity<>(DataResponse.of(
        HttpStatus.OK, "응답 형식 지정 성공", "ok"), HttpStatus.OK);
  }

}
