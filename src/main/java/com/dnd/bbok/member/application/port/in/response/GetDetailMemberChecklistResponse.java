package com.dnd.bbok.member.application.port.in.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class GetDetailMemberChecklistResponse {

  @ApiModelProperty(name = "체크리스트 id")
  private final Long id;

  @ApiModelProperty(name = "체크리스트 설명")
  private final String criteria;

  public GetDetailMemberChecklistResponse(Long id, String criteria) {
    this.id = id;
    this.criteria = criteria;
  }

}
