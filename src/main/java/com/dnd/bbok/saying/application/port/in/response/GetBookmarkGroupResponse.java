package com.dnd.bbok.saying.application.port.in.response;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;

@Getter
public class GetBookmarkGroupResponse {

  @ApiModelProperty(value = "북마크한 글귀 리스트")
  private final List<GetSayingResponse> bookmarks;

  public GetBookmarkGroupResponse(List<GetSayingResponse> sayings) {
    this.bookmarks = sayings;
  }
}
