package com.dnd.friends;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="서버 작동 체크")
@RestController
public class HealthController {

  @Operation(summary = "health-check")
  @GetMapping("/health")
  public String healthCheck() {
    return "hello";
  }

}