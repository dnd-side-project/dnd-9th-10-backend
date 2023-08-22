package com.dnd.bbok.friend.application.port.in.response;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;

/**
 * 친구 목록 response Dto
 */
@Getter
public class GetFriendGroupResponse {

  @ApiModelProperty(value = "친구 목록")
  private final List<GetFriendResponse> friends;

  public GetFriendGroupResponse(List<GetFriendResponse> friends) {
    this.friends = friends;
  }

}