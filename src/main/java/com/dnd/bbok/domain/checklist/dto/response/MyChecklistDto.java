package com.dnd.bbok.domain.checklist.dto.response;

import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * 내 기준 정보(적합 and 부적합) response Dto
 */
@Getter
public class MyChecklistDto {

  @ApiModelProperty(value = "이상적인 기준")
  private final List<ChecklistInfoDto> badChecklist = new ArrayList<>();

  @ApiModelProperty(value = "기피하는 기준")
  private final List<ChecklistInfoDto> goodChecklist = new ArrayList<>();

  /**
   * mock을 위한 기본 생성
   */
  public MyChecklistDto() {
    badChecklist.add(new ChecklistInfoDto());
    goodChecklist.add(new ChecklistInfoDto());
  }

}
