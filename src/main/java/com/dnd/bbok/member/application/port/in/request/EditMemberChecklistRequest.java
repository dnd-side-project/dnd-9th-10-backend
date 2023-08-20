package com.dnd.bbok.member.application.port.in.request;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EditMemberChecklistRequest {

  @ApiModelProperty(name = "수정하려는 이상적인 기준리스트")
  private List<ChecklistInfoRequest> goodChecklist;

  @ApiModelProperty(name = "수정하려는 기피 기준리스트")
  private List<ChecklistInfoRequest> badChecklist;

}
