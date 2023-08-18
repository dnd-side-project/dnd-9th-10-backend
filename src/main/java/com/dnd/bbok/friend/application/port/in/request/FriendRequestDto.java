package com.dnd.bbok.friend.application.port.in.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendRequestDto {

  @ApiModelProperty(value = "친구 이름")
  private String name;

  @ApiModelProperty(value = "친구 캐릭터")
  private String character;

}
