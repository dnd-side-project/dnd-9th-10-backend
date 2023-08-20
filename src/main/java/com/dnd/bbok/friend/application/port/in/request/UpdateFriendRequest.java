package com.dnd.bbok.friend.application.port.in.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateFriendRequest {

  @ApiModelProperty(name = "친구 이름")
  private String name;

}
