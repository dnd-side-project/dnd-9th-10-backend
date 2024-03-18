package com.dnd.bbok.member.application.port.in.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifiedChecklistInfoRequest {

  @ApiModelProperty(name = "수정할 기준 Id")
  private Long id;

  @ApiModelProperty(name = "사용 여부")
  private Boolean isUsed;

}
