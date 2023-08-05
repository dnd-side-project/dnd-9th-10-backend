package com.dnd.bbok.domain.checklist.dto.response;

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

  /**
   * mock을 위한 기본생성자
   */
  public ChecklistInfoDto() {
    this.id = 3L;
    this.criteria = "기준 설명";
  }

}
