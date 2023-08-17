package com.dnd.bbok.member.application.port.out;

import com.dnd.bbok.infra.feign.dto.response.KakaoUserInfoResponse;
import com.dnd.bbok.member.domain.Member;

public interface UpdateMemberPort {

  void updateInfo(KakaoUserInfoResponse kakaoInfo, Member member);

}
