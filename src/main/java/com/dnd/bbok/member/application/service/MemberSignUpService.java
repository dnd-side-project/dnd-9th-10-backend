package com.dnd.bbok.member.application.service;

import com.dnd.bbok.global.exception.BusinessException;
import com.dnd.bbok.infra.feign.dto.response.KakaoUserInfoResponse;
import com.dnd.bbok.member.application.port.in.response.LoginResponse;
import com.dnd.bbok.member.application.port.in.usecase.RegisterMemberUseCase;

import com.dnd.bbok.member.application.port.out.CreateMemberPort;
import com.dnd.bbok.member.application.port.out.JwtTokenPort;
import com.dnd.bbok.member.application.port.out.LoadMemberPort;
import com.dnd.bbok.member.application.port.out.SaveMemberPort;
import com.dnd.bbok.member.application.port.out.UpdateMemberPort;

import com.dnd.bbok.member.domain.Member;
import com.dnd.bbok.member.domain.OAuth2Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 멤버 회원가입 및 로그인 관련 서비스
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MemberSignUpService implements RegisterMemberUseCase {

  private final LoadMemberPort loadMemberPort;
  private final CreateMemberPort createMemberPort;
  private final SaveMemberPort saveMemberPort;
  private final UpdateMemberPort updateMemberPort;
  private final JwtTokenPort jwtTokenPort;

  @Override
  public LoginResponse loginKakaoMember(KakaoUserInfoResponse kakaoUserInfo) {
    //카카오 회원 Id를 변조해서 검사해본다.(회원 Id로 해야만 고유성을 가질 수 있기 때문에)
    String userNumber = String.format("%s#%s", OAuth2Provider.KAKAO, kakaoUserInfo.getId());
    try {
      Member loginMember = loadMemberPort.loadByUserNumber(userNumber);
      updateMemberPort.updateInfo(kakaoUserInfo, loginMember);
      return createAllToken(loginMember);
    } catch (BusinessException e) {
      Member member = createMemberPort.createKakaoMember(kakaoUserInfo);
      Member savedMember = saveMemberPort.saveMember(member);
      return createAllToken(savedMember);
    }
  }

  private LoginResponse createAllToken(Member member) {
    String accessToken = jwtTokenPort.createAccessToken(member);
    String refreshToken = jwtTokenPort.createRefreshToken(member);
    jwtTokenPort.saveRefreshTokenInRedis(member, refreshToken);
    return new LoginResponse(accessToken, refreshToken, member.getId().toString());
  }
}
