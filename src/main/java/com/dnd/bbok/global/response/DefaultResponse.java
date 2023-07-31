package com.dnd.bbok.global.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * 공통된 응답 형식(상태 코드, 응답 메시지)
 */
@Getter
public abstract class DefaultResponse {

  @ApiModelProperty(value = "응답 코드")
  protected int status;

  @ApiModelProperty(value = "응답 메시지")
  protected String message;

  protected DefaultResponse(int status, String message) {
    this.status = status;
    this.message = message;
  }

}
