package com.dnd.bbok.saying.application.port.in.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateBookmarkRequest {

  @ApiModelProperty(value = "북마크하는 명언 Id")
  private Long id;

}
