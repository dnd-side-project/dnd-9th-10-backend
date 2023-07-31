package com.dnd.bbok.domain.friend.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * 캐릭터 1개에 대한 response Dto
 */
@Getter
public class BbokCharacterDto {

  @ApiModelProperty(value = "캐릭터 아이콘 Url")
  private final String iconUrl;

  @ApiModelProperty(value = "캐릭터 이름")
  private final String name;

  /**
   * mock 제공을 위한 기본생성
   */
  public BbokCharacterDto() {
    this.iconUrl = "https://i.ibb.co/6v7YXHZ/cactus.png";
    this.name = "인장이";
  }

}
