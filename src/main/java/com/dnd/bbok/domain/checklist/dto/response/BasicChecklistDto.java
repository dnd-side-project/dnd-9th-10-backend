package com.dnd.bbok.domain.checklist.dto.response;

import com.dnd.bbok.domain.checklist.entity.BasicChecklist;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class BasicChecklistDto {

  @ApiModelProperty(value = "기본 이상적인 체크리스트")
  private final List<String> goodChecklist = new ArrayList<>();

  @ApiModelProperty(value = "기본 이상적인 체크리스트")
  private final List<String> badChecklist = new ArrayList<>();

  public BasicChecklistDto(List<BasicChecklist> checklists) {
    checklists.forEach((ele) -> {
      if (ele.isGood()) {
        goodChecklist.add(ele.getCriteria());
      } else {
        badChecklist.add(ele.getCriteria());
      }
    });
  }
}
