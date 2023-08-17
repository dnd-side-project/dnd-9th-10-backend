package com.dnd.bbok.member.application.port.out;

import com.dnd.bbok.infra.feign.dto.response.KakaoUserInfoResponse;
import com.dnd.bbok.member.domain.Member;

public interface CreateMemberPort {

  Member createGuest();

  Member createKakaoMember(KakaoUserInfoResponse kakaoUserInfo);

}
