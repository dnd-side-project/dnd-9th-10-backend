package com.dnd.bbok.member.application.port.in.request;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EditMemberChecklistRequest {

  @ApiModelProperty(name = "적합(true) or 부적합(false)")
  private Boolean isGood;

  @ApiModelProperty(name = "수정하려는 기준리스트")
  private List<ChecklistInfoRequest> checklist;

}
