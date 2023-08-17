package com.dnd.bbok.member.adapter.out.persistence;

import static com.dnd.bbok.member.domain.OAuth2Provider.GUEST;
import static com.dnd.bbok.member.domain.OAuth2Provider.KAKAO;
import static com.dnd.bbok.member.domain.Role.ROLE_GUEST;
import static com.dnd.bbok.member.domain.Role.ROLE_SOCIAL;

import com.dnd.bbok.infra.feign.dto.response.KakaoUserInfoResponse;
import com.dnd.bbok.member.application.port.out.CreateMemberPort;
import com.dnd.bbok.member.domain.Member;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberSignUpAdapter implements CreateMemberPort {


  @Override
  public Member createGuest() {
    return Member.builder()
        .userNumber(
            String.format("%s#%s", GUEST, UUID.randomUUID().toString().split("-")[0])
        )
        .username("NONE")
        .role(ROLE_GUEST)
        .profileUrl("https://i.ibb.co/34SNTwm/image.png")
        .oAuth2Provider(GUEST)
        .build();
  }

  @Override
  public Member createKakaoMember(KakaoUserInfoResponse kakaoUserInfo) {
    return Member.builder()
        .username(kakaoUserInfo.getUsername())
        .userNumber(
            String.format("%s#%s", KAKAO, kakaoUserInfo.getId())
        )
        .role(ROLE_SOCIAL)
        .oAuth2Provider(KAKAO)
        .profileUrl(kakaoUserInfo.getProfileImg())
        .build();
  }

}
