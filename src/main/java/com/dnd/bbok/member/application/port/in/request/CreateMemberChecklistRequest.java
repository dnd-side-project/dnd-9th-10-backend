package com.dnd.bbok.member.application.port.in.request;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateMemberChecklistRequest {

  @ApiModelProperty(value = "사용자 정의 이상적인 기준 리스트")
  private List<AddedChecklistInfo> goodChecklist;

  @ApiModelProperty(value = "사용자 정의 기피하는 기준 리스트")
  private List<AddedChecklistInfo> badChecklist;

}
