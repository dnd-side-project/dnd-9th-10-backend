package com.dnd.bbok.domain.friend.dto.request;

import static com.dnd.bbok.global.exception.ErrorCode.CHARACTER_NOT_FOUND;

import com.dnd.bbok.domain.friend.entity.BbokCharacter;
import com.dnd.bbok.domain.friend.entity.Friend;
import com.dnd.bbok.domain.member.entity.Member;
import com.dnd.bbok.global.exception.BusinessException;
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

  public Friend toEntity(Member member) {
    return Friend.builder()
        .active(true)
        .name(name)
        .bbok(changeType(character))
        .friendScore(0L)
        .member(member)
        .build();
  }
  
  private BbokCharacter changeType(String character) {
    BbokCharacter bbok;
    switch (character) {
      case "CACTUS":
        bbok = BbokCharacter.SIDE_CACTUS;
        break;
      case "HEDGEHOG":
        bbok = BbokCharacter.SIDE_HEDGEHOG;
        break;
      default:
        throw new BusinessException(CHARACTER_NOT_FOUND);
    }
    return bbok;
  }
}
