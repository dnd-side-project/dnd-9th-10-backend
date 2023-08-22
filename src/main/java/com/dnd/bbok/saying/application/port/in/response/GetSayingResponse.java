package com.dnd.bbok.saying.application.port.in.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class GetSayingResponse {

  @ApiModelProperty(value = "명언 Id")
  private final Long id;

  @ApiModelProperty(value = "명언 내용")
  private final String contents;

  @ApiModelProperty(value = "명언 출처")
  private final String reference;

  public GetSayingResponse(Long id, String contents, String reference) {
    this.id = id;
    this.contents = contents;
    this.reference = reference;
  }
}
