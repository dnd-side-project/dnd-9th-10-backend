package com.dnd.bbok.domain.member.controller;

import com.dnd.bbok.domain.member.dto.response.LoginResponseDto;
import com.dnd.bbok.domain.member.dto.response.MemberInfoResponseDto;
import com.dnd.bbok.domain.member.dto.response.MemberSimpleInfoResponse;
import com.dnd.bbok.domain.member.service.MemberInfoService;
import com.dnd.bbok.domain.member.service.MemberSignUpService;
import com.dnd.bbok.global.jwt.SessionUser;
import com.dnd.bbok.global.response.DataResponse;
import com.dnd.bbok.infra.feign.dto.response.KakaoUserInfoResponseDto;
import com.dnd.bbok.infra.feign.service.KakaoFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "멤버 관련 컨트롤러")
public class MemberController {

  private final KakaoFeignService kakaoFeignService;
  private final MemberSignUpService memberSignUpService;
  private final MemberInfoService memberInfoService;

  /**
   * 게스트 로그인 관련 컨트롤러
   */
  @ApiOperation(value = "게스트 회원가입")
  @PostMapping("/api/v1/guest/signup")
  public ResponseEntity<DataResponse<MemberSimpleInfoResponse>> guestLogin() {

    LoginResponseDto guestLoginResponse = memberSignUpService.loginGuestMember();
    HttpHeaders headers = memberSignUpService.setCookieAndHeader(guestLoginResponse);

    return new ResponseEntity<>(DataResponse.of(HttpStatus.CREATED,
        "게스트 회원 가입 성공", guestLoginResponse.getMember()), headers, HttpStatus.CREATED);
  }

  /**
   * 유저가 자기 자신의 정보에 대해 알 수 있다.
   */
  @ApiOperation(value = "내 정보 조회")
  @GetMapping("/api/v1/member")
  public ResponseEntity<DataResponse<MemberInfoResponseDto>> getMember(
      @AuthenticationPrincipal SessionUser sessionUser
  ) {
    MemberInfoResponseDto memberInfo = memberInfoService.getMember(sessionUser.getUuid());
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.OK, "멤버 정보 조회 성공", memberInfo), HttpStatus.OK);
  }

  /**
   * 로그인 요청을 통해 인가코드를 redirect url로 발급 가능
   */
  @ApiOperation(value = "인가 코드 발급")
  @GetMapping("/api/v1/login/kakao")
  public ResponseEntity<HttpHeaders> getKakaoAuthCode()  {
    HttpHeaders httpHeaders = kakaoFeignService.kakaoLogin();
    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
  }

  /**
   * 인가코드를 통해 accessToken 과 유저 정보를 가져온다.
   */
  @ApiOperation(value = "카카오 계정 회원가입")
  @PostMapping("/api/v1/kakao/signup")
  public ResponseEntity<DataResponse<MemberSimpleInfoResponse>> kakaoLogin(@RequestParam("code") String code) {

    //코드를 통해 액세스 토큰 발급한 후, 유저 정보를 가져온다.
    KakaoUserInfoResponseDto kakaoUserInfo = kakaoFeignService.getKakaoInfoWithToken(code);
    LoginResponseDto kakaoLoginResponse = memberSignUpService.loginKakaoMember(kakaoUserInfo);
    HttpHeaders headers = memberSignUpService.setCookieAndHeader(kakaoLoginResponse);

    return new ResponseEntity<>(
        DataResponse.of(
            HttpStatus.CREATED, "카카오 계정으로 회원가입 성공", kakaoLoginResponse.getMember()), headers, HttpStatus.CREATED);
  }

}
