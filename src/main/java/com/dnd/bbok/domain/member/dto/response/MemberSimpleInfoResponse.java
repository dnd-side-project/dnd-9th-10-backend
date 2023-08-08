package com.dnd.bbok.domain.member.dto.response;

import com.dnd.bbok.domain.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class MemberSimpleInfoResponse {

  @ApiModelProperty("member의 고유 Id")
  private final String memberId;

  public MemberSimpleInfoResponse(Member member) {
    this.memberId = member.getId().toString();
  }

}
