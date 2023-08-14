package com.dnd.bbok.domain.friend.dto.response;

import com.dnd.bbok.domain.friend.entity.Friend;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import lombok.Getter;

/**
 * 친구 1명에 대한 response Dto
 */
@Getter
public class FriendDto {

  @ApiModelProperty(value = "친구 고유 ID")
  private final Long id;

  @ApiModelProperty(value = "친구 생성 날짜")
  private final LocalDate startedAt;

  @ApiModelProperty(value = "친구 캐릭터 아이콘 url")
  private final String characterUrl;

  @ApiModelProperty(value = "친구 이름")
  private final String name;

  @ApiModelProperty(value = "친구 관련 일화 수")
  private final int countingDiary;

  @ApiModelProperty(value = "친구 점수")
  private final Long score;

  @ApiModelProperty(value = "친구 활성화 상태")
  private final boolean isActive;

  public FriendDto(Friend friend, String iconUrl, int diaryCount) {
    this.id = friend.getId();
    this.startedAt = friend.getCreatedAt();
    this.characterUrl = iconUrl;
    this.name = friend.getName();
    this.countingDiary = diaryCount;
    this.score = friend.getFriendScore();
    this.isActive = friend.isActive();
  }

}
