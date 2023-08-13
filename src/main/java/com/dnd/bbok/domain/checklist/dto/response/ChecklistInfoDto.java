package com.dnd.bbok.domain.checklist.dto.response;

import com.dnd.bbok.domain.checklist.entity.MemberChecklist;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * 내 기준 정보(적합 or 부적합) response Dto
 */
@Getter
public class ChecklistInfoDto {

  @ApiModelProperty(value = "기준 아이디")
  private final Long id;

  @ApiModelProperty(value = "기준 설명")
  private final String criteria;

  public ChecklistInfoDto(MemberChecklist memberChecklist) {
    this.id = memberChecklist.getId();
    this.criteria = memberChecklist.getCriteria();
  }

}
