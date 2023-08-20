package com.dnd.bbok.friend.application.port.in.response;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;

/**
 * 친구 목록 response Dto
 */
@Getter
public class FriendGroupInfo {

  @ApiModelProperty(value = "친구 목록")
  private final List<FriendInfo> friends;

  public FriendGroupInfo(List<FriendInfo> friends) {
    this.friends = friends;
  }

}