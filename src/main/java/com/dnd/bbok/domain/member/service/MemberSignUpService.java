package com.dnd.bbok.domain.member.service;

import com.dnd.bbok.domain.jwt.dto.ReIssueTokenDto;
import com.dnd.bbok.global.util.CookieUtil;
import com.dnd.bbok.global.util.HttpHeaderUtil;
import com.dnd.bbok.infra.feign.dto.response.KakaoUserInfoResponseDto;
import com.dnd.bbok.domain.jwt.service.JwtTokenProvider;
import com.dnd.bbok.domain.member.dto.response.LoginResponseDto;
import com.dnd.bbok.domain.member.entity.Member;
import com.dnd.bbok.domain.member.entity.OAuth2Provider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberSignUpService {

  private final JwtTokenProvider jwtTokenProvider;
  private final MemberService memberService;

  public LoginResponseDto loginGuestMember() {
    Member guest = memberService.saveGuestMember();
    return createSignUpResult(guest);
  }

  public LoginResponseDto loginKakaoMember(KakaoUserInfoResponseDto kakaoUserInfo) {
    //카카오 회원 Id를 변조해서 검사해본다.(회원 Id로 해야만 고유성을 가질 수 있기 때문에)
    String userNumber = String.format("%s#%s", OAuth2Provider.KAKAO, kakaoUserInfo.getId());
    Optional<Member> loginMember = memberService.getMemberByUserNumber(userNumber);

    //만약 존재한다면, update 친다.
    if(loginMember.isPresent()) {
      Member member = loginMember.get();
      updateMemberInfo(kakaoUserInfo, member);
      //토큰 새로 생성이 아닌, 이후에 refreshToken 체크해서 accessToken을 다시 발급해주는 로직을 넣어야 함.
      return createSignUpResult(member);
    }

    //존재하지 않는다면, user를 생성해서 넣어준다.
    Member member = signUp(kakaoUserInfo);
    return createSignUpResult(member);
  }

  /**
   * member 정보를 가지고 accessToken 과 refreshToken 을 생성한다.
   */
  private LoginResponseDto createSignUpResult(Member member) {
    String accessToken = jwtTokenProvider.createAccessToken(member.getId(), member.getRole());
    String refreshToken = jwtTokenProvider.createRefreshToken(member.getId());

    // refreshToken은 redis에 따로 저장해둔다.
    jwtTokenProvider.saveRefreshTokenInRedis(member, refreshToken);
    return new LoginResponseDto(accessToken, refreshToken, member.getId().toString());
  }

  private Member signUp(KakaoUserInfoResponseDto kakaoUserInfo) {
    Member member = kakaoUserInfo.toEntity();
    return memberService.saveInfo(member);
  }

  private void updateMemberInfo(KakaoUserInfoResponseDto kakaoUserInfo, Member member) {
    member.updateInfo(kakaoUserInfo.getProfileImg(), kakaoUserInfo.getUsername());
  }

  public static HttpHeaders setCookieAndHeader(LoginResponseDto loginResult) {
    HttpHeaders headers = new HttpHeaders();
    CookieUtil.setRefreshCookie(headers, loginResult.getRefreshToken());
    HttpHeaderUtil.setAccessToken(headers, loginResult.getAccessToken());
    return headers;
  }

  public static HttpHeaders setCookieAndHeader(ReIssueTokenDto reIssueTokenDto) {
    HttpHeaders headers = new HttpHeaders();
    HttpHeaderUtil.setAccessToken(headers, reIssueTokenDto.getAccessToken());
    CookieUtil.setRefreshCookie(headers, reIssueTokenDto.getRefreshToken());
    return headers;
  }

}
