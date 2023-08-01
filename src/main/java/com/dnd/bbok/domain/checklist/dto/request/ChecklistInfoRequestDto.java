package com.dnd.bbok.domain.checklist.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 기준 수정 request Dto
 */
@Getter
@NoArgsConstructor
public class ChecklistInfoRequestDto {

  @ApiModelProperty(value = "수정하려는 기준 Id")
  private Long id;

  @ApiModelProperty(value = "수정된 기준")
  private String criteria;

}
