package com.dnd.bbok.domain.saying.dto.response;

import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class BookmarkInfoDto {

  @ApiModelProperty(value = "북마크한 글귀 리스트")
  private final List<SayingInfoDto> bookmarks = new ArrayList<>();

  /**
   * mock 을 위한 기본 생성자
   */
  public BookmarkInfoDto() {
    bookmarks.add(new SayingInfoDto());
  }

}
