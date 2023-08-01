package com.dnd.bbok.domain.friend.dto.response;

import com.dnd.bbok.domain.friend.entity.BbokCharacter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * 캐릭터 1개에 대한 response Dto
 */
@Getter
public class BbokCharacterDto {

  @ApiModelProperty(value = "캐릭터 타입(인장이, 고스미)")
  private final BbokCharacter type;

  @ApiModelProperty(value = "캐릭터 아이콘 Url")
  private final String iconUrl;

  @ApiModelProperty(value = "캐릭터 이름")
  private final String name;

  /**
   * mock 제공을 위한 기본생성
   */
  public BbokCharacterDto() {
    this.type = BbokCharacter.CACTUS;
    this.iconUrl = "https://i.ibb.co/6v7YXHZ/cactus.png";
    this.name = this.type.getName();
  }

}
