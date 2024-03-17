package com.dnd.bbok.member.application.port.in.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class GetDetailMemberChecklistResponse {

  @ApiModelProperty(name = "체크리스트 id")
  private final Long id;

  @ApiModelProperty(name = "체크리스트 설명")
  private final String criteria;

  @ApiModelProperty(name = "사용 여부")
  private final boolean isUsed;

  public GetDetailMemberChecklistResponse(Long id, String criteria, boolean isUsed) {
    this.id = id;
    this.criteria = criteria;
    this.isUsed = isUsed;
  }

}
