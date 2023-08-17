package com.dnd.bbok.member.application.service;

import com.dnd.bbok.member.application.port.in.response.GetMemberInfoResponse;
import com.dnd.bbok.member.application.port.in.usecase.GetMemberQuery;
import com.dnd.bbok.member.application.port.out.LoadMemberPort;
import com.dnd.bbok.member.domain.Member;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 회원가입 이외의 Member 관련 로직을 담는 서비스
 */
@Service
@RequiredArgsConstructor
@Slf4j
//TODO: 기존 구조에서 MemberService 지울 때, 이름 변경하기
public class MemberTestService implements GetMemberQuery {

  private final LoadMemberPort loadMemberPort;

  @Override
  public GetMemberInfoResponse getMember(UUID memberId) {
    Member member = loadMemberPort.loadById(memberId);
    return new GetMemberInfoResponse(member);
  }
}
