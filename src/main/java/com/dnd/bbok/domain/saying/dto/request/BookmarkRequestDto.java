package com.dnd.bbok.domain.saying.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkRequestDto {

  @ApiModelProperty(value = "북마크하는 명언 Id")
  private Long id;

}
