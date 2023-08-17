package com.dnd.bbok.member.application.port.in.usecase;

import com.dnd.bbok.infra.feign.dto.response.KakaoUserInfoResponse;
import com.dnd.bbok.member.application.port.in.response.LoginResponse;

/**
 * member 등록 관련 UseCase
 */
public interface RegisterMemberUseCase {

  LoginResponse signUpGuest();

  LoginResponse loginKakaoMember(KakaoUserInfoResponse kakaoUserInfo);
}
