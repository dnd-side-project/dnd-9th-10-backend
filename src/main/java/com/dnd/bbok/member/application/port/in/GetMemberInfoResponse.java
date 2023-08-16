package com.dnd.bbok.member.application.port.in;

import static com.dnd.bbok.member.adapter.out.persistence.entity.OAuth2Provider.*;

import com.dnd.bbok.member.adapter.out.persistence.entity.OAuth2Provider;
import com.dnd.bbok.member.domain.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class GetMemberInfoResponse {

  @ApiModelProperty("member의 프로필 이미지 url")
  private final String profileUrl;

  @ApiModelProperty("member의 고유 Id")
  private final String memberId;

  @ApiModelProperty("member의 이름")
  private final String memberName;

  @ApiModelProperty("member의 oauth 제공자(GUEST, KAKAO)")
  private final OAuth2Provider oAuth2Provider;

  //TODO: 게스트 멤버 프로필 이미지 S3를 통해서 불러오기 추가, 가입까지 완료되어야 기능 추가할 수 있음
  public GetMemberInfoResponse(Member member) {
    this.profileUrl = member.getProfileUrl();
    this.memberId = member.getId().toString();
    this.memberName = (member.getOAuth2Provider().equals(GUEST)) ?
        member.getUserNumber() : member.getUsername();
    this.oAuth2Provider = member.getOAuth2Provider();
  }

}
