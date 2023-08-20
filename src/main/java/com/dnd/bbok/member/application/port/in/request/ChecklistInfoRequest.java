package com.dnd.bbok.member.application.port.in.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChecklistInfoRequest {

  @ApiModelProperty(name = "수정할 기준 Id")
  private Long id;

  @ApiModelProperty(name = "수정할 내용")
  private String criteria;

}
