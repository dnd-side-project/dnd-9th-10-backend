package com.dnd.bbok.domain.checklist.dto.request;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberChecklistRequestDto {

  @ApiModelProperty(value = "사용자 정의 이상적인 기준 리스트")
  private List<String> goodChecklist;

  @ApiModelProperty(value = "사용자 정의 기피하는 기준 리스트")
  private List<String> badChecklist;

}
