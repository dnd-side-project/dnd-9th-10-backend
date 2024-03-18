package com.dnd.bbok.member.application.port.in.request;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EditMemberChecklistRequest {
  @ApiModelProperty(name = "수정하려는 적합 체크리스트")
  private List<ModifiedChecklistInfoRequest> modifiedGoodChecklist;

  @ApiModelProperty(name = "추가하려는 적합 체크리스트")
  private List<AddedChecklistInfo> addedGoodChecklist;

  @ApiModelProperty(name = "수정하려는 부적합 체크리스트")
  private List<ModifiedChecklistInfoRequest> modifiedBadChecklist;

  @ApiModelProperty(name = "추가하려는 부적합 체크리스트")
  private List<AddedChecklistInfo> addedBadChecklist;
}
