package com.dnd.bbok.domain.friend.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 친구 등록 request Dto
 */
@Getter
@NoArgsConstructor
public class FriendRequestDto {

  @ApiModelProperty(value = "친구 이름")
  private String name;

  @ApiModelProperty(value = "친구 캐릭터")
  private String character;


}
