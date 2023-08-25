package com.dnd.bbok.friend.application.port.in.response;

import com.dnd.bbok.friend.domain.Friend;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * 친구 1명에 대한 response Dto
 */
@Getter
public class GetFriendResponse {

  @ApiModelProperty(value = "친구 고유 ID")
  private final Long id;

  @ApiModelProperty(value = "친구 생성 날짜")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private final LocalDateTime startedAt;

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

  public GetFriendResponse(Friend friend, String iconUrl, int diaryCount) {
    this.id = friend.getId();
    this.startedAt = friend.getCreatedAt();
    this.characterUrl = iconUrl;
    this.name = friend.getName();
    this.countingDiary = diaryCount;
    this.score = friend.getFriendScore();
    this.isActive = friend.isActive();
  }

}
