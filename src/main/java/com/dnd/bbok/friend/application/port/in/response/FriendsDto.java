package com.dnd.bbok.friend.application.port.in.response;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;

/**
 * 친구 목록 response Dto
 */
@Getter
public class FriendsDto {

  @ApiModelProperty(value = "친구 목록")
  private final List<FriendDto> friends;

  public FriendsDto(List<FriendDto> friends) {
    this.friends = friends;
  }

}