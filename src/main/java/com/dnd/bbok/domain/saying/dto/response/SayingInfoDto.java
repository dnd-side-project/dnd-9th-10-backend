package com.dnd.bbok.domain.saying.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class SayingInfoDto {

  @ApiModelProperty(value = "명언 Id")
  private final Long id;

  @ApiModelProperty(value = "명언 내용")
  private final String contents;

  @ApiModelProperty(value = "명언 출처")
  private final String reference;

  public SayingInfoDto() {
    this.id = 1L;
    this.contents = "궁극적으로 관계에서 유대감을 형성하는 것은 대화다.";
    this.reference = "책";
  }

}
