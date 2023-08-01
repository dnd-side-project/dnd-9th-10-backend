package com.dnd.bbok.domain.checklist.dto.request;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyChecklistRequestDto {

  @ApiModelProperty(value = "수정된 이상적인 기준 리스트")
  private List<ChecklistInfoRequestDto> goodChecklist;

  @ApiModelProperty(value = "수정된 기피하는 기준 리스트")
  private List<ChecklistInfoRequestDto> badChecklist;

}
