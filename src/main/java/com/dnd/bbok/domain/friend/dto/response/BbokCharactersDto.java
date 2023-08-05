package com.dnd.bbok.domain.friend.dto.response;

import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * 서비스 캐릭터 전체에 대한 response Dto
 */
@Getter
public class BbokCharactersDto {

  @ApiModelProperty(value = "캐릭터 목록")
  private final List<BbokCharacterDto> characters = new ArrayList<>();

  /**
   * mock 제공을 위한 기본생성
   */
  public BbokCharactersDto() {
    characters.add(new BbokCharacterDto());
  }



}
