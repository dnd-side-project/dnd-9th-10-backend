package com.dnd.bbok.basicchecklist.application.port.in;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import lombok.Getter;

@Getter
public class GetBasicChecklistResponse {

  @ApiModelProperty(value = "기본 이상적인 체크리스트")
  private final List<String> goodChecklist;

  @ApiModelProperty(value = "기본 이상적인 체크리스트")
  private final List<String> badChecklist;


  public GetBasicChecklistResponse(List<String> goodChecklist, List<String> badChecklist) {
    this.goodChecklist = goodChecklist;
    this.badChecklist = badChecklist;
  }
}
