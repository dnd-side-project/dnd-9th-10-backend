package com.dnd.bbok.domain.friend.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * 친구 1명에 대한 response Dto
 */
@Getter
public class FriendDto {

  @ApiModelProperty(value = "친구 고유 ID")
  private final Long id;

  @ApiModelProperty(value = "친구 생성 날짜 카운팅")
  private final int countingDay;

  @ApiModelProperty(value = "친구 캐릭터 아이콘 url")
  private final String characterUrl;

  @ApiModelProperty(value = "친구 이름")
  private final String name;

  @ApiModelProperty(value = "친구 관련 일화 수")
  private final int countingDiary;

  @ApiModelProperty(value = "친구 점수")
  private final Long score;

  @ApiModelProperty(value = "친구 활성화 상태")
  private final boolean status;

  /**
   * mock 제공을 위한 기본생성자
   */
  public FriendDto() {
    this.id = 1L;
    this.countingDay = 23;
    this.characterUrl = "https://i.ibb.co/ZgfdtSY/hedgehog.png";
    this.name = "김도리";
    this.countingDiary = 6;
    this.score = 58L;
    this.status = true;
  }

}
