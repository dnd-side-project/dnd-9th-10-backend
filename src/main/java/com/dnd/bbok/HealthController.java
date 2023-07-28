package com.dnd.bbok;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

}
