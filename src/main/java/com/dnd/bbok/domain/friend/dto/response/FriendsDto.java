package com.dnd.bbok.domain.friend.dto.response;

import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * 친구 목록 response Dto
 */
@Getter
public class FriendsDto {

  @ApiModelProperty(value = "친구 목록")
  private final List<FriendDto> friends = new ArrayList<>();

  public FriendsDto() {
    FriendDto friend = new FriendDto();
    FriendDto friend2 = new FriendDto();
    friends.add(friend);
    friends.add(friend2);
  }

}
