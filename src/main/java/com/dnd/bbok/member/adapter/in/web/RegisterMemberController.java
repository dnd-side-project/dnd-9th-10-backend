package com.dnd.bbok.member.adapter.in.web;

import com.dnd.bbok.global.response.DataResponse;
import com.dnd.bbok.infra.feign.dto.response.KakaoUserInfoResponse;
import com.dnd.bbok.infra.feign.service.KakaoFeignService;
import com.dnd.bbok.member.application.port.in.response.LoginResponse;
import com.dnd.bbok.member.application.port.in.usecase.RegisterMemberUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "멤버 관련 컨트롤러")
public class RegisterMemberController {
  private final RegisterMemberUseCase registerMemberUseCase;
  private final KakaoFeignService kakaoFeignService;

  /**
   * 게스트 로그인 관련 컨트롤러
   */
  @ApiOperation(
      value = "게스트 회원가입",
      notes = "요청 보내면 바로 게스트가 생성되고, accessToken이 발급됩니다.")
  @PostMapping("/api/v1/guest/signup")
  public ResponseEntity<DataResponse<LoginResponse>> guestSignup() {
    LoginResponse guestLoginResponse = registerMemberUseCase.signUpGuest();
    return new ResponseEntity<>(DataResponse.of(HttpStatus.CREATED,
        "게스트 회원 가입 성공", guestLoginResponse), HttpStatus.CREATED);
  }

  /**
   * 로그인 요청을 통해 인가코드를 redirect url로 발급 가능
   */
  @ApiOperation(
      value = "인가 코드 발급",
      notes = "해당 url을 통해 로그인 화면으로 넘어간 후, 사용자가 정보를 입력하면 redirect url에서 코드를 발급할 수 있습니다.")
  @GetMapping("/api/v1/kakao/login")
  public ResponseEntity<HttpHeaders> getKakaoAuthCode()  {
    HttpHeaders httpHeaders = kakaoFeignService.kakaoLogin();
    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
  }

  /**
   * 인가코드를 통해 accessToken 과 유저 정보를 가져온다.
   */
  @ApiOperation(
      value = "카카오 계정 회원가입",
      notes = "인가 코드를 입력하고 요청보내면, 사용자의 정보를 저장한 후 사용자의 Id를 확인할 수 있습니다.")
  @GetMapping("/api/v1/account/kakao/result")
  public ResponseEntity<DataResponse<LoginResponse>> kakaoLogin(@RequestParam("code") String code) {
    //코드를 통해 액세스 토큰 발급한 후, 유저 정보를 가져온다.
    KakaoUserInfoResponse kakaoUserInfo = kakaoFeignService.getKakaoInfoWithToken(code);
    LoginResponse kakaoLoginResponse = registerMemberUseCase.loginKakaoMember(kakaoUserInfo);
    return new ResponseEntity<>(
        DataResponse.of(
            HttpStatus.CREATED, "카카오 계정으로 회원가입 성공", kakaoLoginResponse), HttpStatus.CREATED);
  }

}
