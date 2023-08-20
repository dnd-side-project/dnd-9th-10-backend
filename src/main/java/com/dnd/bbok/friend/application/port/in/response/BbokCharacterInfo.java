package com.dnd.bbok.friend.application.port.in.response;

import com.dnd.bbok.friend.domain.BbokCharacter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class BbokCharacterInfo {

  @ApiModelProperty(value = "캐릭터 타입(CACTUS, HEDGEHOG)")
  private final String type;

  @ApiModelProperty(value = "캐릭터 아이콘 Url")
  private final String iconUrl;

  @ApiModelProperty(value = "캐릭터 이름(인장이, 고스미)")
  private final String name;


  public BbokCharacterInfo(BbokCharacter character, String signedUrl) {
    this.type = character.getType();
    this.iconUrl = signedUrl;
    this.name = character.getName();
  }

}

