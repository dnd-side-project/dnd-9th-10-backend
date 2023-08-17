package com.dnd.bbok.member.application.port.in;

import com.dnd.bbok.member.domain.ChecklistInfo;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;

/**
 * 내 기준 정보(적합 and 부적합) response Dto
 */
@Getter
public class GetMemberChecklistResponse {

  @ApiModelProperty(value = "이상적인 기준")
  private final List<ChecklistInfo> badChecklist;

  @ApiModelProperty(value = "기피하는 기준")
  private final List<ChecklistInfo> goodChecklist;

  public GetMemberChecklistResponse(List<ChecklistInfo> good, List<ChecklistInfo> bad) {
    this.goodChecklist = good;
    this.badChecklist = bad;
  }

}
