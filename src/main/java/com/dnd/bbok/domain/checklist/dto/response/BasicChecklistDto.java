package com.dnd.bbok.domain.checklist.dto.response;

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

  /**
   * mock 을 위한 기본 생성자
   */
  public BasicChecklistDto() {
    goodChecklist.add("좋은 기준1");
    goodChecklist.add("좋은 기준2");

    badChecklist.add("나쁜 기준1");
    badChecklist.add("나쁜 기준2");
  }

}
