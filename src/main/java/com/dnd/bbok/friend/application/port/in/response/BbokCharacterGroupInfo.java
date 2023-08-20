package com.dnd.bbok.friend.application.port.in.response;

import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * 서비스 캐릭터 전체에 대한 response Dto
 */
@Getter
public class BbokCharacterGroupInfo {

  @ApiModelProperty(value = "캐릭터 목록")
  private final List<BbokCharacterInfo> characters = new ArrayList<>();

  public BbokCharacterGroupInfo(List<BbokCharacterInfo> dtos) {
    characters.addAll(dtos);
  }

}

